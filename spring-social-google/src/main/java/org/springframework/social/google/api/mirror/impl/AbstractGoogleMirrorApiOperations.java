package org.springframework.social.google.api.mirror.impl;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.*;
import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Common operations for working with Google Glass' Mirror HTTP APIs.
 *
 * @author Josh Long
 */
public class AbstractGoogleMirrorApiOperations extends AbstractGoogleApiOperations {

	/** Entry point for the Google Glass Mirror API */
	public static final String DEFAULT_BASE_URL = "https://www.googleapis.com/mirror/v1";
	/**
	 * Requests may not be sent to non-SSL encrypted callback URLs. During development, Google recommends using the Mirror
	 * Notifications SSL Proxy to forward the requests to your insecure HTTP callbacks during development.
	 */
	public static final String DEFAULT_SSL_FORWARDING_DEVELOPMENT_TIME_PROXY = "https://mirrornotifications.appspot.com/forward";
 	private Log logger = LogFactory.getLog(getClass());
	private boolean useDevelopmentProxiedCallbackUrls = false;
	private String serviceUrl;

	public AbstractGoogleMirrorApiOperations(RestTemplate restTemplate, boolean isAuthorized) {
		this(restTemplate, isAuthorized, true);
	}

	/**
	 * @param useDevelopmentProxiedCallbackUrls
	 * 		  when set, this dictates whether the callbackUrl given for location subscriptions should be sent as-is, or
	 * 		  encoded as a request parameter to Google's development-time proxy, which can then
	 */
	public AbstractGoogleMirrorApiOperations(RestTemplate restTemplate, boolean isAuthorized, boolean useDevelopmentProxiedCallbackUrls) {
		super(restTemplate, isAuthorized);
		this.serviceUrl = DEFAULT_BASE_URL;
		this.useDevelopmentProxiedCallbackUrls = useDevelopmentProxiedCallbackUrls;
	}

	protected String callbackUrl(String url) {
		if (this.useDevelopmentProxiedCallbackUrls){
			return DEFAULT_SSL_FORWARDING_DEVELOPMENT_TIME_PROXY + "?url=" + url;
		}
		return url;
	}

	protected String getServiceUrl() {
		return this.serviceUrl;
	}

	protected void info(String msg, Object... parms) {
		logger.info(String.format(msg, parms));
	}

	protected void debug(Object o) {
		if (o != null){
			logger.debug(ToStringBuilder.reflectionToString(o));
		}
	}

	protected void info(Object o) {
		if (o != null){
			logger.info(ToStringBuilder.reflectionToString(o));
		}
	}

	protected void debug(String msg, Object... parms) {
		logger.debug(String.format(msg, parms));
	}
}
