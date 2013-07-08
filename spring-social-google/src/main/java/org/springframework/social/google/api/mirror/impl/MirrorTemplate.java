package org.springframework.social.google.api.mirror.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.mirror.*;
import org.springframework.web.client.RestTemplate;

/**
 * Handles operations against the Google Glass Mirror API.
 *
 * @author Josh Long
 */
public class MirrorTemplate
		  extends AbstractGoogleApiOperations
		  implements MirrorOperations {

	private SubscriptionOperations subscriptionOperations;
	private LocationOperations locationOperations;
	private ContactOperations contactOperations;
	private TimelineOperations timelineOperations;

	public  MirrorTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
		initialize();
	}

	private void initialize() {
		try {
			this.subscriptionOperations = new SubscriptionTemplate(this.restTemplate, this.isAuthorized);
			this.contactOperations = new ContactTemplate(this.restTemplate, this.isAuthorized);
			this.timelineOperations = new TimelineTemplate(this.restTemplate, this.isAuthorized);
			this.locationOperations = new LocationTemplate(this.restTemplate, this.isAuthorized);
		}
		catch (Exception er) {
			throw new RuntimeException(er);
		}
	}

	@Override
	public SubscriptionOperations subscriptionOperations() {
		return this.subscriptionOperations;
	}

	@Override
	public LocationOperations locationOperations() {
		return this.locationOperations;
	}

	@Override
	public TimelineOperations timelineOperations() {
		return this.timelineOperations;
	}

	@Override
	public ContactOperations contactOperations() {
		return this.contactOperations;
	}
}
