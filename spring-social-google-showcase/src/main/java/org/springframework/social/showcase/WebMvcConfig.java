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

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.*;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.*;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.UrlBasedViewResolver;
import org.springframework.web.servlet.view.tiles2.*;

import java.util.*;
import java.util.concurrent.*;

/**
 * Spring MVC Configuration.
 *
 * @author Craig Walls
 * @author Josh Long
 * @author Rob Winch
 */
@Configuration
@ComponentScan (excludeFilters = {@ComponentScan.Filter (Configuration.class)})
@Import ({SecurityConfig.class, ServiceConfig.class, SocialConfig.class})
//@ImportResource ("classpath:/security.xml")
@EnableWebMvc
public class WebMvcConfig extends WebMvcConfigurerAdapter {

	@Bean
	public ViewResolver viewResolver() {
		UrlBasedViewResolver viewResolver = new UrlBasedViewResolver();
		viewResolver.setViewClass(TilesView.class);
		return viewResolver;
	}

	@Bean
	public TilesConfigurer tilesConfigurer() {
		TilesConfigurer configurer = new TilesConfigurer();
		configurer.setDefinitions(new String[]{
				                                        "/WEB-INF/layouts/tiles.xml",
				                                        "/WEB-INF/views/**/tiles.xml"
		});
		configurer.setCheckRefresh(true);
		return configurer;
	}

	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}

	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("/WEB-INF/messages/messages");
		return messageSource;
	}

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addWebRequestInterceptor(new CommonPropertiesRegisteringWebRequestInterceptor());
	}
}

class CommonPropertiesRegisteringWebRequestInterceptor implements WebRequestInterceptor {

	private Map<String, Set<String>> providerScopes =
			  new ConcurrentHashMap<String, Set<String>>();

	public CommonPropertiesRegisteringWebRequestInterceptor() {
		registerGoogleScopes();
	}

	private void registerGoogleScopes() {
		String googlePrefix = "https://www.googleapis.com/auth/";
		String[] scopes = {
				                    "plus.login",
				                    "plus.me",
				                    "blogger",
				                    "drive",
				                    "glass.timeline",
				                    "glass.location",
				                    "userinfo.profile",
				                    "drive",
				                    "drive.apps.readonly",
				                    "drive.file",
				                    "drive.metadata.readonly",
				                    "drive.readonly",
				                    "drive.scripts"
		};
		Set<String> aggregate = new ConcurrentSkipListSet<String>();
		for (String s : scopes) {
			aggregate.add(googlePrefix + s);
		}
		this.providerScopes.put("google", aggregate);
	}

	@Override
	public void preHandle(WebRequest request) throws Exception {
	}

	/* run after the request handler, but before the view is rendered. */
	@Override
	public void postHandle(WebRequest request, ModelMap model) throws Exception {
		for (Map.Entry<String, Set<String>> providerEntry : this.providerScopes.entrySet()) {
			String providerName = providerEntry.getKey();
			String joinedString = StringUtils.collectionToDelimitedString(this.providerScopes.get(providerName), " ");
			request.setAttribute(providerName + ".scopes", joinedString, RequestAttributes.SCOPE_REQUEST);

		}
	}

	@Override
	public void afterCompletion(WebRequest request, Exception ex) throws Exception {
	}
}