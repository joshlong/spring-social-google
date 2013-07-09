package org.springframework.social.google.api.mirror.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.http.*;
import org.springframework.social.google.api.mirror.*;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.net.URI;
import java.util.*;

/**
 * Handles operations against the Google Glass timeline. A timeline is a virtual sequence composed of a specific type of
 * {@link org.springframework.social.google.api.mirror.TimelineItem}
 *
 * @author Josh Long
 */
public class TimelineTemplate extends AbstractGoogleMirrorApiOperations implements TimelineOperations {

	private String timelineIdParam = "{timelineId}";
	private String attachmentsIdParam = "{attachmentsId}";
	private String timeline = "timeline";
	private String attachments = "attachments";

	public TimelineTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);

	}

	public URI timelineUri() {
		return this.uri("/" + timeline).build().toUri();
	}

	public URI timelineAttachmentsUri(String timelineId) {
		Map<String, String> parms = new HashMap<String, String>();
		parms.put(this.timelineIdParam, timelineId);
		return this.uri("/" + this.timeline + "/" + this.timelineIdParam + "/" + this.attachments).buildAndExpand(parms).toUri();
	}

	public URI timelineAttachmentsUri(String timelineId, String attachmentsId) {
		Map<String, String> parms = new HashMap<String, String>();
		parms.put(this.timelineIdParam, timelineId);
		parms.put(this.attachmentsIdParam, attachmentsId);
		return this.uri("/" + this.timeline + "/" + this.timelineIdParam + "/" + this.attachments + "/" + this.attachmentsIdParam).buildAndExpand(parms).toUri();
	}

	public URI timelineUri(String id) {
		Map<String, String> idParam = Collections.singletonMap(this.timelineIdParam, id);
		return this.uri("/" + timeline).fragment("/" + this.timelineIdParam).buildAndExpand(idParam).toUri();
	}

	/*
	 * builds an HttpEntity that can create the request that the API is expecting.
	 */
	HttpEntity<?> requestForTimelineItem(TimelineItem timelineItem) {

		return null;
	}

	@Override
	public Card insertCard(Card card) {
		URI cardUri = timelineUri();
		HttpEntity<?> cardEntity = this.requestForTimelineItem(card);
		ResponseEntity<JsonNode> responseEntity = restTemplate.postForEntity(cardUri, cardEntity, JsonNode.class);
		return from(responseEntity);
	}

	Card from(ResponseEntity<JsonNode> nodeResponseEntity) {
		return null;
	}

	@Override
	public Bundle insertBundle(Card[] cards) {
		return null;
	}

	@Override
	public List<TimelineItem> getTimelineItems() {
		return null;
	}

	@Override
	public TimelineItem update(String id, TimelineItem ti) {
		return null;
	}

	@Override
	public TimelineItem patch(String id, TimelineItem ti) {
		return null;
	}

	@Override
	public TimelineItem update(String id, TimelineItem ti, InputStream inputStream) {
		return null;
	}

	static interface HttpResponseMap extends Map<String, String> {
	}

	//	public com.google.api.services.mirror.Mirror.Timeline.Delete delete(java.lang.String s) throws java.io.IOException { /* compiled code */ }

//	public com.google.api.services.mirror.Mirror.Timeline.Get get(java.lang.String s) throws java.io.IOException { /* compiled code */ }

//	public com.google.api.services.mirror.Mirror.Timeline.Insert insert(com.google.api.services.mirror.model.TimelineItem timelineItem) throws java.io.IOException { /* compiled code */ }

//	public com.google.api.services.mirror.Mirror.Timeline.Insert insert(com.google.api.services.mirror.model.TimelineItem timelineItem, com.google.api.client.http.AbstractInputStreamContent abstractInputStreamContent) throws java.io.IOException { /* compiled code */ }

//	public com.google.api.services.mirror.Mirror.Timeline.List list() throws java.io.IOException { /* compiled code */ }

//	public com.google.api.services.mirror.Mirror.Timeline.Patch patch(java.lang.String s, com.google.api.services.mirror.model.TimelineItem timelineItem) throws java.io.IOException { /* compiled code */ }

//	public com.google.api.services.mirror.Mirror.Timeline.Update update(java.lang.String s, com.google.api.services.mirror.model.TimelineItem timelineItem) throws java.io.IOException { /* compiled code */ }

//	public com.google.api.services.mirror.Mirror.Timeline.Update update(java.lang.String s, com.google.api.services.mirror.model.TimelineItem timelineItem, com.google.api.client.http.AbstractInputStreamContent abstractInputStreamContent) throws java.io.IOException { /* compiled code */ }

//	public com.google.api.services.mirror.Mirror.Timeline.Attachments attachments() { /* compiled code */ }


}
        