package org.springframework.social.google.api.mirror.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.mirror.LocationOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Handles operations against the Google Glass LocationTemplate
 *
 * @author Josh Long
 */
public class LocationTemplate extends AbstractGoogleApiOperations implements LocationOperations {
	protected LocationTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}
}
        