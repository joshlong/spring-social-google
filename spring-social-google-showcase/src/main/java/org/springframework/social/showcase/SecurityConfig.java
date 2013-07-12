/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.showcase;

import org.springframework.context.annotation.*;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.encrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.rememberme.*;
import org.springframework.security.web.savedrequest.*;
import org.springframework.social.UserIdSource;
import org.springframework.social.connect.UsersConnectionRepository;
import org.springframework.social.security.*;

import javax.inject.*;
import javax.sql.DataSource;

@Configuration
@EnableGlobalMethodSecurity (prePostEnabled = true)
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {



	@Inject
	private DataSource dataSource;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		String signin = "/signin", signup = "/signup", signout = "/signout";
		String asterisk = "/**";


		http.rememberMe().useSecureCookie(false).userDetailsService(this.userDetailsService()) ;
		// .rememberMeServices(  );

		http.formLogin()
				  .loginPage(signin).loginProcessingUrl(signin + "/authenticate").usernameParameter("j_username").passwordParameter("j_password").permitAll(true);

		http.logout()
				  .logoutUrl(signout).deleteCookies("JSESSIONID");

		http.authorizeUrls()
				  .antMatchers("/favicon.ico", "/resources" + asterisk, signin + asterisk, signout + asterisk, signup + asterisk).permitAll()
				  .anyRequest().authenticated();


	}

	@Bean
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Bean
	@Override
	public UserDetailsService userDetailsServiceBean() throws Exception {
		return super.userDetailsServiceBean();
	}

	@Override
	protected void registerAuthentication(AuthenticationManagerBuilder auth) throws Exception {

		String usersByUsernameQuery = "select username, password, true from user_account where username = ?";

		String authoritiesByUsernameQuery = "select username, 'ROLE_USER' from user_account where username = ?";

		auth.jdbcAuthentication()
				  .authoritiesByUsernameQuery(authoritiesByUsernameQuery)
				  .usersByUsernameQuery(usersByUsernameQuery)
				  .passwordEncoder(passwordEncoder())
				  .dataSource(this.dataSource);


	}

	@Bean
	public RequestCache requestCache() {
		return new HttpSessionRequestCache();
	}

	@Bean
	public TextEncryptor textEncryptor() {
		return Encryptors.noOpText();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	// Social Security configuration

	@Bean
	public PersistentTokenRepository persistentTokenRepository(DataSource dataSource, JdbcTemplate jdbcTemplate) {
		JdbcTokenRepositoryImpl persistentTokenRepository = new JdbcTokenRepositoryImpl();
		persistentTokenRepository.setDataSource(dataSource);
		persistentTokenRepository.setJdbcTemplate(jdbcTemplate);
		persistentTokenRepository.setCreateTableOnStartup(false);
		return persistentTokenRepository;
	}

	@Bean
	public RememberMeServices rememberMeServices(UserDetailsService userDetailsService, PersistentTokenRepository persistentTokenRepository) {
		PersistentTokenBasedRememberMeServices persistentTokenBasedRememberMeServices = new PersistentTokenBasedRememberMeServices("google", userDetailsService, persistentTokenRepository);
		persistentTokenBasedRememberMeServices.setAlwaysRemember(true);
		return persistentTokenBasedRememberMeServices;
	}

	@Bean
	public AuthenticationProvider socialAuthenticationProvider(UsersConnectionRepository usersConnectionRepository, UserDetailsService userDetailsService) {
		return new SocialAuthenticationProvider(usersConnectionRepository, socialUsersDetailsService(userDetailsService));
	}

	@Bean
	public SocialUserDetailsService socialUsersDetailsService(UserDetailsService userDetailsService) {
		return new SimpleSocialUsersDetailService(userDetailsService);
	}

	@Bean
	public UserIdSource userIdSource() {
		return new AuthenticationUserIdExtractor();
	}

	@Bean
	public SocialAuthenticationFilter socialAuthenticationFilter(
			                                                              AuthenticationManager authenticationManager,
			                                                              RememberMeServices rememberMeServices,
			                                                              UsersConnectionRepository usersConnectionRepository,
			                                                              UserIdSource userIdSource,
			                                                              SocialAuthenticationServiceLocator authenticationServiceLocator) {

		SocialAuthenticationFilter socialAuthenticationFilter = new SocialAuthenticationFilter(authenticationManager, userIdSource, usersConnectionRepository, authenticationServiceLocator);
		socialAuthenticationFilter.setSignupUrl("/signup"); // TODO: Fix filter to handle in-app paths
		socialAuthenticationFilter.setRememberMeServices(rememberMeServices);
		return socialAuthenticationFilter;
	}

	public static class SimpleSocialUsersDetailService implements SocialUserDetailsService {

		private UserDetailsService userDetailsService;

		public SimpleSocialUsersDetailService(UserDetailsService userDetailsService) {
			this.userDetailsService = userDetailsService;
		}

		@Override
		public SocialUserDetails loadUserByUserId(String userId) throws UsernameNotFoundException, DataAccessException {
			UserDetails userDetails = userDetailsService.loadUserByUsername(userId);
			return new SocialUser(userDetails.getUsername(), userDetails.getPassword(), userDetails.getAuthorities());
		}


	}

	public static class AuthenticationUserIdExtractor implements UserIdSource {
		@Override
		public String getUserId() {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication == null){
				throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
			}
			return authentication.getName();
		}
	}

}
