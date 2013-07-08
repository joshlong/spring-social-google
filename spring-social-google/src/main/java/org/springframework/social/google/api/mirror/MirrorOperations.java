package org.springframework.social.google.api.mirror;

/**
 * Handles operations against the Google Glass Mirror API.
 * Implemented by {@link org.springframework.social.google.api.mirror.impl.MirrorTemplate}.
 *
 * @author Josh Long
 */
public interface MirrorOperations {
	/*
	* API for working subscriptions, which are callbacks for events in the Glass API
	* like timeline updates, shares, or custom menu item callbacks.
	*/
	SubscriptionOperations subscriptionOperations();

	/** API for working with location updates */
	LocationOperations locationOperations();

	/**
	 * API for manipulating items in the timeline, which is the virtual sequence of <code>cards</code> presented to the
	 * user.
	 */
	TimelineOperations timelineOperations();

	/**
	 * API for manipulating contacts, including the ability to register the
	 * as a candidate application with which other applications may <code>share</code> content and data.
	 */
	ContactOperations contactOperations();
}
        