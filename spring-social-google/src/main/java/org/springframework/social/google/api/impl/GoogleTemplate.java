/*
 * Copyright 2011 the original author or authors.
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
package org.springframework.social.google.api.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aopalliance.intercept.*;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.*;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.http.*;
import org.springframework.http.client.*;
import org.springframework.http.converter.*;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.drive.DriveOperations;
import org.springframework.social.google.api.drive.impl.DriveTemplate;
import org.springframework.social.google.api.mirror.MirrorOperations;
import org.springframework.social.google.api.mirror.impl.MirrorTemplate;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.api.plus.impl.PlusTemplate;
import org.springframework.social.google.api.tasks.TaskOperations;
import org.springframework.social.google.api.tasks.impl.TaskTemplate;
import org.springframework.social.google.api.userinfo.UserInfoOperations;
import org.springframework.social.google.api.userinfo.impl.UserInfoTemplate;
import org.springframework.social.oauth2.*;

import java.io.*;
import java.lang.reflect.Method;
import java.util.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static com.fasterxml.jackson.databind.DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES;
import static com.fasterxml.jackson.databind.SerializationFeature.FAIL_ON_EMPTY_BEANS;
import static com.fasterxml.jackson.databind.SerializationFeature.WRITE_DATES_AS_TIMESTAMPS;
import static org.springframework.util.ReflectionUtils.findMethod;
import static org.springframework.util.ReflectionUtils.invokeMethod;

/**
 * <p> The central class for interacting with Google APIs. </p> <p> Most of the APIs, specifically all GData APIs and
 * Google+ usage of "me", require OAuth2 authentication. To use methods that require OAuth2 authentication, {@link
 * GoogleTemplate} must be constructed with an access token which is authorized for the appropriate scope. For using
 * Google+ without authenticating, {@link GoogleTemplate} default constructor can be used. </p>
 *
 * @author Gabriel Axel
 */
public class GoogleTemplate extends AbstractOAuth2ApiBinding implements Google {

	private boolean forwardNonSslMirrorNotificationUrlsThroughGoogleProxy = true ;
	private String accessToken;
	private UserInfoOperations userOperations;
	private PlusOperations plusOperations;
	private TaskOperations taskOperations;
	private MirrorOperations mirrorOperations;
	private DriveOperations driveOperations;

	/**
	 * Creates a new instance of GoogleTemplate. This constructor creates a new GoogleTemplate able to perform
	 * unauthenticated operations against Google+.
	 */
	public GoogleTemplate() {
		initialize();
	}

	/**
	 * Creates a new instance of GoogleTemplate. This constructor creates the FacebookTemplate using a given access token.
	 *
	 * @param accessToken
	 * 		  an access token granted by Google after OAuth2 authentication
	 */
	public GoogleTemplate(String accessToken) {
		super(accessToken);
		this.accessToken = accessToken;
		initialize();
	}

	private void initialize() {
		getRestTemplate().getInterceptors().add(new LoggingClientHttpRequestInterceptor());
		userOperations = new UserInfoTemplate(getRestTemplate(), isAuthorized());
		plusOperations = new PlusTemplate(getRestTemplate(), isAuthorized());
		mirrorOperations = new MirrorTemplate(getRestTemplate(), isAuthorized(), this.forwardNonSslMirrorNotificationUrlsThroughGoogleProxy);
		taskOperations = new TaskTemplate(getRestTemplate(), isAuthorized());
		driveOperations = new DriveTemplate(getRestTemplate(), isAuthorized());
	}

	@Override
	protected List<HttpMessageConverter<?>> getMessageConverters() {

		MappingJackson2HttpMessageConverter jsonConverter = new MappingJackson2HttpMessageConverter();
		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.configure(FAIL_ON_UNKNOWN_PROPERTIES, false);
		objectMapper.configure(WRITE_DATES_AS_TIMESTAMPS, false);
		objectMapper.configure(FAIL_ON_EMPTY_BEANS, false);
		objectMapper.setSerializationInclusion(NON_NULL);
		jsonConverter.setObjectMapper(objectMapper);

		FormHttpMessageConverter formHttpMessageConverter = new FormHttpMessageConverter();
		formHttpMessageConverter.addPartConverter(jsonConverter);

		List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
		messageConverters.add(jsonConverter);
		messageConverters.add(new ByteArrayHttpMessageConverter());
		messageConverters.add(formHttpMessageConverter);
		return messageConverters;
	}

	@Override
	protected OAuth2Version getOAuth2Version() {
		return OAuth2Version.BEARER;
	}

	@Override
	public MirrorOperations mirrorOperations() {
		return mirrorOperations;
	}

	@Override
	public UserInfoOperations userOperations() {
		return userOperations;
	}

	@Override
	public PlusOperations plusOperations() {
		return plusOperations;
	}

	@Override
	public TaskOperations taskOperations() {
		return taskOperations;
	}

	@Override
	public DriveOperations driveOperations() {
		return driveOperations;
	}

	@Override
	public void applyAuthentication(Object client) {
		Method setHeaders = findMethod(client.getClass(), "setHeader", String.class, String.class);
		invokeMethod(setHeaders, client,
				              "Authorization", getOAuth2Version().getAuthorizationHeaderValue(accessToken));
	}

	@Override
	public String getAccessToken() {
		return accessToken;
	}

	public static class LoggingClientHttpRequestInterceptor implements ClientHttpRequestInterceptor {
		private Log logger = LogFactory.getLog(getClass());

		private void line(String k, String v) {
			String title = StringUtils.capitalize(k).trim();
			String attrValue = StringUtils.defaultIfEmpty(v, "").trim();
			logger.info(String.format("%s=%s", title, attrValue));

		}

		@Override
		public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws
				  IOException {
			try {
				final ClientHttpResponse innerResponse = execution.execute(request, body);
				final byte[] bytesOfInputStream = IOUtils.toByteArray(innerResponse.getBody());

				MediaType mediaType = innerResponse.getHeaders().getContentType();
				if (logger.isInfoEnabled()){

					logger.info(StringUtils.repeat("=", 50));
					logger.info("REQUEST");
					logger.info(StringUtils.repeat("-", 50));
					HttpHeaders httpHeaders = request.getHeaders();
					String accept = httpHeaders.getAccept().size() > 0 ?
							                  httpHeaders.getAccept().iterator().next().toString() : "";
					line("uri", request.getURI().toString());
					line("accept", accept);
					line("request", new String(body));
					line("http method", request.getMethod().toString());
					logger.info("");


					logger.info(StringUtils.repeat("=", 50));
					logger.info("RESPONSE");
					logger.info(StringUtils.repeat("-", 50));
					if (mediaType != null){
						line("media-type", mediaType.toString());
					}
					line("response", new String(bytesOfInputStream));
					line("status code", innerResponse.getStatusCode().toString());
					logger.info("");

					MethodInterceptor methodInterceptor = new MethodInterceptor() {
						@Override
						public Object invoke(MethodInvocation invocation) throws Throwable {
							if (invocation.getMethod().getName().equals("getBody")){
								return new ByteArrayInputStream(bytesOfInputStream);
							}
							else {
								return invocation.proceed();
							}
						}
					};
					ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
					proxyFactoryBean.setTarget(innerResponse);
					proxyFactoryBean.addAdvice(methodInterceptor);
					proxyFactoryBean.setAutodetectInterfaces(true);

					proxyFactoryBean.setProxyInterfaces(innerResponse.getClass().getInterfaces());

					proxyFactoryBean.setProxyTargetClass(false);
					return (ClientHttpResponse) proxyFactoryBean.getObject();
				}
			}
			catch (Exception e) {
				throw new RuntimeException(e);
			}
			return null;
		}
	}
}


