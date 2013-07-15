package org.springframework.social.google.api.mirror.impl;

import org.springframework.social.google.api.mirror.*;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.List;

/**
 * A REST client for working with the Google Glass timeline resource as documented at <A
 * href="https://developers.google.com/glass/v1/reference/timeline/list">the timeline resource documentation.</A>.
 * <p/>
 * The timeline itself is composed of {@link TimelineItem timeline items}.
 *
 * @author Josh Long
 */
public class TimelineTemplate extends AbstractGoogleMirrorApiOperations implements TimelineOperations {
	private String timeline = "timeline";


	public TimelineTemplate(RestTemplate restTemplate, boolean isAuthorized) {
		super(restTemplate, isAuthorized);
	}

	@Override
	public TimelineItemQueryBuilder timelineQuery() {
		return new TimelineItemQueryBuilderImpl(this.timelineUri(), this.restTemplate);
	}

	@Override
	public TimelineItemsPage getTimelineItems() {
		return getTimelineItems(null);
	}

	@Override
	public TimelineItemsPage getTimelineItems(String pageTokenId) {
		TimelineItemQueryBuilder timelineItemQueryBuilder = timelineQuery();
		if (StringUtils.hasText(pageTokenId)){
			timelineItemQueryBuilder.fromPage(pageTokenId);
		}
		return timelineItemQueryBuilder.getPage();
	}

	protected String timelineItemUri(String itemId) {
		String timelineUri = this.timelineUri();
		return timelineUri + "/" + itemId;
	}

	@Override
	public void deleteTimelineItem(String timelineItemId) {
		this.restTemplate.delete(this.timelineItemUri(timelineItemId));
	}

	@Override
	public void deleteTimelineItem(TimelineItem timelineItem) {
		deleteTimelineItem(timelineItem.getId());
	}

	@Override
	public void clearTimeline() {

		TimelineItemsPage timelineItemsPage = null;

		String pageTokenId = null;

		while ((timelineItemsPage = getTimelineItems(pageTokenId)) != null) {
			pageTokenId = timelineItemsPage.getNextPageToken();

			List<TimelineItem> items = timelineItemsPage.getItems();

			if ((items.size() == 0) || !StringUtils.hasText(pageTokenId)){
				break;
			}

			for (TimelineItem timelineItem : items) {
				deleteTimelineItem(timelineItem);
			}
		}
	}

	protected String timelineUri() {
		String url = this.getServiceUrl() + "/" + this.timeline;
		return URI.create(url).toString();
	}


	@Override
	public TimelineItem insertCard(TimelineItem card) {
		return saveEntity(timelineUri(), card);
	}
}
