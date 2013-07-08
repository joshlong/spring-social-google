package org.springframework.social.google.api.mirror.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.*;

/**
 * Common operations for working with Google Glass' Mirror HTTP APIs.
 *
 * @author Josh Long
 */
public class AbstractGoogleMirrorApiOperations extends AbstractGoogleApiOperations {

	public static final String DEFAULT_ROOT_URL = "https://www.googleapis.com/";
	public static final String DEFAULT_BASE_URL = DEFAULT_ROOT_URL + "/mirror/v1/";
	private String serviceUrl;

	public AbstractGoogleMirrorApiOperations(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
		this.serviceUrl = DEFAULT_BASE_URL;
	}

	protected String getServiceUrl() {
		return this.serviceUrl;
	}

	protected UriComponentsBuilder  uri(String ext) {
		return UriComponentsBuilder.fromPath(this.getServiceUrl())
				         .fragment(ext)
				          ;
	}

}
