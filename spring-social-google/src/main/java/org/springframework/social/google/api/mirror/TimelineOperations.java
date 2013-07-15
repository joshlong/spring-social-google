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
	TimelineItemQueryBuilder timelineQuery();

	void deleteTimelineItem(String timelineItemId);

	void deleteTimelineItem(TimelineItem timelineItem);

	// returns the first of this application's timeline items
	TimelineItemsPage getTimelineItems();

	// handles page tokens which can be used for paging.
	TimelineItemsPage getTimelineItems(String pageTokenId);

	/* remove all the cards from the timeline. */
	void clearTimeline();

	TimelineItem insertCard(TimelineItem card);

	/*Bundle insertBundle(Card[] cards);

	List< TimelineItem> getTimelineItems();

	TimelineItem update(String id,  TimelineItem ti);

	TimelineItem patch(String id,   TimelineItem ti);

	TimelineItem update(String id,   TimelineItem ti, InputStream inputStream);*/

}
        