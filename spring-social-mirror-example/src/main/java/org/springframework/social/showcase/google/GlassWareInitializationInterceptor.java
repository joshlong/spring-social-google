package org.springframework.social.showcase.google;

import org.springframework.social.connect.*;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.google.api.Google;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

/**
 * Establishes certain subscriptions like the location and other events.
 *
 * @author Josh Long
 */
public class GlassWareInitializationInterceptor implements ConnectInterceptor<Google> {

	// check box in the setup flow that indicates whether the application can send an introduction card.

	private static final String SEND_INTRODUCTION_CARD = "glass.sendIntroductionCard";

	@Override
	public void preConnect(ConnectionFactory<Google> googleConnectionFactory, MultiValueMap<String, String> stringStringMultiValueMap, WebRequest webRequest) {

	}

	private boolean attribute(WebRequest webRequest, String aN) {
		return (webRequest.getAttribute(aN, WebRequest.SCOPE_REQUEST) + "").equals("true");
	}

	@Override
	public void postConnect(Connection<Google> googleConnection, WebRequest webRequest) {
/*

		Google google = googleConnection.getApi();
		MirrorOperations mirrorOperations = google.mirrorOperations();
		TimelineOperations timelineOperations = mirrorOperations.timelineOperations();
		List<TimelineItem> timelineItemList = timelineOperations.getTimelineItems();

		for (TimelineItem timelineItem : timelineItemList) {
			System.out.println(timelineItem.toString());
		}
*/


	}

}
