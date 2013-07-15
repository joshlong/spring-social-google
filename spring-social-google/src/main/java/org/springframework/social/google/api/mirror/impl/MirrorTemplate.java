package org.springframework.social.google.api.mirror.impl;

import org.springframework.social.google.api.impl.AbstractGoogleApiOperations;
import org.springframework.social.google.api.mirror.*;
import org.springframework.web.client.RestTemplate;

/**
 * Provides a RESTful client for the Google Glass Mirror API, which itself consists of resources to manage
 * <EM>timelines</EM>, <EM>locations</EM>, <em>contacts</em>, and <em>contacts</em>.
 * <p/>
 * Some API endpoints support subscribing to notifications at client-specified callback URIs. To support those callback
 * notifications, see the {@link org.springframework.web.servlet.handler.google.api.mirror the Google Glass Spring MVC
 * handler package}.
 *
 * @author Josh Long
 */
public class MirrorTemplate
		  extends AbstractGoogleApiOperations
		  implements MirrorOperations {

	private boolean forwardNonSslMirrorNotificationUrlsThroughGoogleProxy = true;
	private SubscriptionOperations subscriptionOperations;
	private LocationOperations locationOperations;
	private ContactOperations contactOperations;
	private TimelineOperations timelineOperations;

	public MirrorTemplate(RestTemplate restTemplate, boolean isAuthorized, boolean forwardNonSslMirrorNotificationUrlsThroughGoogleProxy) {
		super(restTemplate, isAuthorized);
		this.forwardNonSslMirrorNotificationUrlsThroughGoogleProxy = forwardNonSslMirrorNotificationUrlsThroughGoogleProxy;
		this.subscriptionOperations = new SubscriptionTemplate(this.restTemplate, this.isAuthorized);
		this.contactOperations = new ContactTemplate(this.restTemplate, this.isAuthorized);
		this.timelineOperations = new TimelineTemplate(this.restTemplate, this.isAuthorized);
		this.locationOperations = new LocationTemplate(this.restTemplate, this.isAuthorized, this.forwardNonSslMirrorNotificationUrlsThroughGoogleProxy);
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
