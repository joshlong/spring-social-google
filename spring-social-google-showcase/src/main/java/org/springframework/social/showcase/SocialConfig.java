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
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.social.UserIdSource;
import org.springframework.social.config.annotation.EnableJdbcConnectionRepository;
import org.springframework.social.connect.*;
import org.springframework.social.connect.web.*;
import org.springframework.social.google.config.annotation.EnableGoogle;
import org.springframework.social.security.AuthenticationNameUserIdSource;
import org.springframework.social.showcase.google.glass.*;
import org.springframework.social.showcase.signin.SimpleSignInAdapter;

/**
 * Example demonstrating common norms for Google Glass based applications.
 *
 * @author Craig Walls
 * @author Josh Long
 */
@Configuration
@EnableGoogle (appId = "${google.clientId}", appSecret = "${google.clientSecret}")
@EnableJdbcConnectionRepository
@PropertySource ("classpath:/application.properties")
public class SocialConfig {

	@Bean
	public ConnectController connectController(ConnectionFactoryLocator connectionFactoryLocator, ConnectionRepository connectionRepository) {
		ConnectController connectController = new ConnectController(connectionFactoryLocator, connectionRepository);
		connectController.addInterceptor(  new GlassWareInitializationInterceptor() );
		return connectController;
	}


	@Bean
	public ProviderSignInController providerSignInController(ConnectionFactoryLocator connectionFactoryLocator,
			                                                          UsersConnectionRepository usersConnectionRepository,
																						 RememberMeServices rememberMeServices ,
			                                                          RequestCache requestCache) {
		ProviderSignInController providerSignInController = new ProviderSignInController(connectionFactoryLocator, usersConnectionRepository, new SimpleSignInAdapter(requestCache));
		providerSignInController.addSignInInterceptor(new GlassWareProviderSignInInterceptor(rememberMeServices ));
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
