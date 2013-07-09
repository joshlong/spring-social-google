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
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableJdbcConnectionRepository;
import org.springframework.social.connect.*;
import org.springframework.social.connect.web.*;
import org.springframework.social.facebook.config.annotation.EnableFacebook;
import org.springframework.social.google.config.annotation.EnableGoogle;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.showcase.google.GlassWareInitializationInterceptor;
import org.springframework.social.showcase.signin.SimpleSignInAdapter;

/**
 * Example demonstrating common norms for Google Glass based applications.
 *
 * @author Craig Walls
 * @author Josh Long
 */
@Configuration
@EnableFacebook(appId = "205992766192598", appSecret = "5fac7e9d41a9bfbbfc3b7e3d2a869dce")
@EnableGoogle (appId = "${google.clientId}", appSecret = "${google.clientSecret}")
@EnableJdbcConnectionRepository
@PropertySource ("classpath:/application.properties")
public class SocialConfig {

/*

	@Bean
	@Scope (value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionFactoryLocator connectionFactoryLocator(Environment environment) {
		ConnectionFactoryRegistry registry = new ConnectionFactoryRegistry();
		registry.addConnectionFactory(new GoogleConnectionFactory(environment.getProperty("google.clientId"), environment.getProperty("google.clientSecret")));
		return registry;
	}

	@Bean
	@Scope (value = "singleton", proxyMode = ScopedProxyMode.INTERFACES)
	public UsersConnectionRepository usersConnectionRepository(DataSource dataSource, ConnectionFactoryLocator connectionFactoryLocator) {
		return new JdbcUsersConnectionRepository(dataSource, connectionFactoryLocator, Encryptors.noOpText());
	}

	@Bean
	@Scope (value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public ConnectionRepository connectionRepository(UsersConnectionRepository usersConnectionRepository) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if (authentication == null){
			throw new IllegalStateException("Unable to get a ConnectionRepository: no user signed in");
		}
		return usersConnectionRepository.createConnectionRepository(authentication.getName());
	}

	@Bean
	@Scope (value = "request", proxyMode = ScopedProxyMode.INTERFACES)
	public Google google(ConnectionRepository connectionRepository) {
		Connection<Google> google = connectionRepository.findPrimaryConnection(Google.class);
		return google != null ? google.getApi() : null;
	}
*/

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
		connectController.addInterceptor(new GlassWareInitializationInterceptor());
		return connectController;
	}



	@Bean
	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator,
			                                                          UsersConnectionRepository usersConnectionRepository,
			                                                          RequestCache requestCache) {
		ProviderSignInController providerSignInController =
				  new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(requestCache));

		return providerSignInController;
	}

	@Bean
	public ReconnectFilter apiExceptionHandler(UsersConnectionRepository usersConnectionRepository, UserIdSource userIdSource) {
		return new ReconnectFilter(usersConnectionRepository, userIdSource);
	}

	@Bean
	public AuthenticationNameUserIdSource authenticationNameUserIdSource() {
		return new AuthenticationNameUserIdSource();
	}


}
