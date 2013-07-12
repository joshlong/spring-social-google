package org.springframework.social.google.api.mirror.impl;

import org.apache.commons.logging.*;
import org.springframework.social.google.api.mirror.*;
import org.springframework.web.client.RestTemplate;

import java.net.URI;

/**
 * Handles operations against the Google Glass timeline. A timeline is a virtual sequence composed of a specific type of
 * {@link org.springframework.social.google.api.mirror.TimelineItem}
 *
 * @author Josh Long
 */
public class TimelineTemplate extends AbstractGoogleMirrorApiOperations implements TimelineOperations {
	private Log logger = LogFactory.getLog(getClass());
	private String timelineIdParam = "{timelineId}";
	private String timeline = "timeline";

	public TimelineTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	private URI timelineUri() {
		String url = this.getServiceUrl() + "/" + this.timeline;
		return URI.create(url);
	}

	@Override
	public TimelineItem insertCard(TimelineItem card) {
		return saveEntity(timelineUri().toString(), card);
	}
}
