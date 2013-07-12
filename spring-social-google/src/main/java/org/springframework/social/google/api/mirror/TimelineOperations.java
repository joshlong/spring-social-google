package org.springframework.social.google.api.mirror;

/**
 * Handles operations against the Google Glass TimeLineOperations.
 * <p/>
 * This is the client API to the API <A href="https://developers.google.com/glass/v1/reference/timeline">published</A>
 * here.
 *
 * @author Josh Long
 */
public interface TimelineOperations {

	TimelineItem insertCard(TimelineItem card);

	/*Bundle insertBundle(Card[] cards);

	List< TimelineItem> getTimelineItems();

	TimelineItem update(String id,  TimelineItem ti);

	TimelineItem patch(String id,   TimelineItem ti);

	TimelineItem update(String id,   TimelineItem ti, InputStream inputStream);*/

}
        