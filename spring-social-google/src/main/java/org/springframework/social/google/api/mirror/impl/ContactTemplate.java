package org.springframework.social.google.api.mirror.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.mirror.ContactOperations;
import org.springframework.web.client.RestTemplate;

/**
 *
 * Handles operations against the Google Glass ContactTemplate 
 * @author Josh Long
 */
public class ContactTemplate extends AbstractGoogleApiOperations implements ContactOperations {

	protected ContactTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}
}
        