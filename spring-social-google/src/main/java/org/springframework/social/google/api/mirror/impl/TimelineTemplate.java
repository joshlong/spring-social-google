package org.springframework.social.google.api.mirror.impl;

import org.springframework.http.*;
import org.springframework.social.google.api.mirror.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

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

	private static final String REST_PATH = "/timeline";

	public TimelineTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);

	}

	public UriComponentsBuilder timelineUri() {
		return this.uri(REST_PATH);
	}

	public UriComponentsBuilder timelineUri(String id) {
		return timelineUri().fragment("/{id}");
	}

	/*
	 * builds an HttpEntity that can create the request that the API is expecting.
	 */
	HttpEntity<?> requestForTimelineItem(TimelineItem timelineItem) {

		return null;
	}

	protected Card buildCard(ResponseEntity<?> entity) {
		return build(entity, Card.class);
	}

	protected Bundle buildBundle(ResponseEntity<?> e) {
		return build(e, Bundle.class);
	}

	protected <T extends TimelineItem> T build(ResponseEntity<?> httpEntity, Class<T> classOfT) {
		try {
			T instance = classOfT.newInstance();
			if (httpEntity.getStatusCode().ordinal() >= 200 &&
			    httpEntity.getStatusCode().ordinal() < 300){

				Object body = httpEntity.getBody();

				if (body instanceof Map){
					Map<String,Object > objectsMap =  (Map<String,Object>) body;




				}
				if (body instanceof TimelineItem){
					T t = (T) body;
					return t;
				}


			}
			return instance;
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Card insertCard(Card card) {
		URI cardUri = timelineUri().build().toUri();
		HttpEntity<?> cardEntity = this.requestForTimelineItem(card);
		ResponseEntity<?> responseEntity = restTemplate.postForEntity(cardUri, cardEntity, ResponseEntity.class);
		return this.build(responseEntity, Card.class);
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
        