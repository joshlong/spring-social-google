package org.springframework.social.google.api.mirror;

import org.springframework.social.google.api.query.ApiQueryBuilder;

/**
 * Supports queries against the timeline collection.
 * <p/>
 * bundleId	string	 If provided, only items with the given bundleId will be returned. includeDeleted	boolean	 If true,
 * tombstone records for deleted items will be returned. maxResults	unsigned integer	 The maximum number of items to
 * include in the response, used for paging. orderBy	string	 Controls the order in which timeline items are returned.
 * <p/>
 * Acceptable values are: "displayTime": Results will be ordered by displayTime (default). This is the same ordering as
 * is used in the timeline on the device. "writeTime": Results will be ordered by the time at which they were last
 * written to the data store. pageToken	string	 Token for the page of results to return. pinnedOnly	boolean	 If true,
 * only pinned items will be returned. sourceItemId	string	 If provided, only items with the given sourceItemId will be
 * returned.
 *
 * @author Josh Long
 */
public interface TimelineItemQueryBuilder extends ApiQueryBuilder<TimelineItemQueryBuilder, TimelineItemsPage> {

	TimelineItemQueryBuilder sourceItemId(String sourceItemId);

	TimelineItemQueryBuilder includeDeleted(boolean includeDeleted);

	TimelineItemQueryBuilder pinnedOnly(boolean pinnedOnly);

	TimelineItemQueryBuilder orderBy(TimelineItemsOrder timelineItemsOrder);

	TimelineItemQueryBuilder bundleId(String bundleId);

}
