package org.springframework.social.google.api.mirror;

import java.util.Date;

/** @author Josh Long */
public class Notification {

	private Date deliveryTime = new Date();
	private NotificationLevel level = NotificationLevel.DEFAULT;

	public Notification() {
	}

	public Notification(NotificationLevel level, Date deliveryTime) {
		this.level = level;
		this.deliveryTime = deliveryTime;
	}

	public NotificationLevel getLevel() {
		return this.level;
	}

	public Date getDeliveryTime() {
		return this.deliveryTime;
	}
}