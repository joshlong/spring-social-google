package org.springframework.social.google.api.mirror.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.mirror.SubscriptionOperations;
import org.springframework.web.client.RestTemplate;

/**
 * Handles operations against the Google Glass SubscriptionTemplate
 *
 * @author Josh Long
 */
public class SubscriptionTemplate
		  extends AbstractGoogleApiOperations
		  implements SubscriptionOperations {

	protected SubscriptionTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}
}
        