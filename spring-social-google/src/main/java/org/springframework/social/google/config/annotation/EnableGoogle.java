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
package org.springframework.social.google.config.annotation;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * Annotation to enable Google APIs in a Spring Social application.
 *
 * Configures useful Google API clients like a {@link org.springframework.social.google.connect.GoogleConnectionFactory},
 * a {@link org.springframework.social.google.config.GoogleApiHelper}, and
 * a {@link org.springframework.social.google.security.GoogleAuthenticationService}.
 *
 * The {@link org.springframework.social.google.connect.GoogleConnectionFactory} provides most of what powers
 * the Oauth integration, in Spring MVC.
 *
 * @author Craig Walls
 */
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@Import(GoogleProviderConfigRegistrar.class)
public @interface EnableGoogle {

	/**
	 * The application's consumer key as issued by Twitter.
	 */
	String appId();
	
	/**
	 * The application's consumer secret as issued by Twitter.
	 */
	String appSecret();
	
}
