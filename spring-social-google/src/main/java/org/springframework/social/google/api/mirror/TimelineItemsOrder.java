package org.springframework.social.google.api.mirror;

import org.springframework.util.Assert;

/**
 * In what order should collections of {@link TimelineItem timeline items} returned from the Timeline collection be
 * ordered?
 *
 * @author Josh Long
 */
public enum TimelineItemsOrder {

	/**
	 * Results will be ordered by displayTime (default). This is the same ordering as is used in the timeline on the
	 * device
	 */
	DISPLAY_TIME("displayTime"),

	/** Results will be ordered by the time at which they were last written to the data store. */
	WRITE_TIME("writeTime");


	private String jsonPropertyName;

	TimelineItemsOrder(String jsonPropertyName) {
		this.jsonPropertyName = jsonPropertyName;
		Assert.hasText(this.jsonPropertyName, "You must specify a version of the enum to be used in JSON");
	}

	@Override
	public String toString() {
		return this.jsonPropertyName;
	}

}
