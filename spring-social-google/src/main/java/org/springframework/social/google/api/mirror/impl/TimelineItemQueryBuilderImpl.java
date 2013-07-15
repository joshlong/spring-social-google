package org.springframework.social.google.api.mirror.impl;

import org.springframework.social.google.api.mirror.*;
import org.springframework.social.google.api.query.impl.ApiQueryBuilderImpl;
import org.springframework.web.client.RestTemplate;

/** @author Josh Long */
class TimelineItemQueryBuilderImpl
		  extends ApiQueryBuilderImpl<TimelineItemQueryBuilder, TimelineItemsPage>
		  implements TimelineItemQueryBuilder {


	private TimelineItemsOrder orderBy = TimelineItemsOrder.DISPLAY_TIME;

	private String sourceItemId;
	private boolean includeDeleted;
	private boolean pinnedOnly;
	private String bundleId;

	public TimelineItemQueryBuilderImpl(String timelineUri, RestTemplate restTemplate) {
		super(timelineUri, TimelineItemsPage.class, restTemplate);
	}

	@Override
	protected StringBuilder build() {
		StringBuilder stringBuilder = super.build();
		appendQueryParam(stringBuilder, "bundleId", this.bundleId);
		if (this.pinnedOnly){
			appendQueryParam(stringBuilder, "pinnedOnly", this.pinnedOnly);
		}
		if (this.includeDeleted){
			appendQueryParam(stringBuilder, "includeDeleted", this.includeDeleted);
		}
		appendQueryParam(stringBuilder, "sourceItemId", this.sourceItemId);
		appendQueryParam(stringBuilder, "orderBy", this.orderBy);

		return stringBuilder;
	}

	@Override
	public TimelineItemQueryBuilder sourceItemId(String sourceItemId) {
		this.sourceItemId = sourceItemId;
		return this;
	}

	@Override
	public TimelineItemQueryBuilder includeDeleted(boolean includeDeleted) {
		this.includeDeleted = includeDeleted;
		return this;
	}

	protected void appendQueryParam(StringBuilder sb, String name, TimelineItemsOrder value) {
		if (value != null){
			appendQueryParam(sb, name, value.toString());
		}
	}

	@Override
	public TimelineItemQueryBuilder pinnedOnly(boolean pinnedOnly) {
		this.pinnedOnly = pinnedOnly;
		return this;
	}

	@Override
	public TimelineItemQueryBuilder orderBy(TimelineItemsOrder timelineItemsOrder) {
		this.orderBy = timelineItemsOrder;
		return this;
	}

	@Override
	public TimelineItemQueryBuilder bundleId(String bundleId) {
		this.bundleId = bundleId;
		return this;
	}
}
