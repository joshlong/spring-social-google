package org.springframework.social.google.api.mirror;

import org.springframework.social.google.api.plus.Activity;

import java.util.*;

/**
 * Parent type for things which may be managed by a {@link Timeline timeline}, including <em>cards</em> and
 * <EM>bundles</EM> (which are aggregates of cards).
 *
 * @author Josh Long
 */
public class TimelineItem extends MirrorApiEntity {
	private final static String KIND = "mirror#timelineItem";
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	private String inReplyTo;
	private Date displayTime;



	private Activity.Attachment[] attachments;
	private Contact creator;
	private List<Contact> recipients = new ArrayList<Contact>();
	private Location location;
	private Date created;
	private boolean pinned;
	private String bundleId;
	private boolean deleted;
	private String text;
	private String title;
	private List<String> htmlPages;
	private String sourceItemId;
	private boolean bundleCover;
	private int pinScore;
	private String speakableText;
	private String canonicalUrl;
	private String selfLink;
	private Notification notification;
	private String html;
	private Date updated;

	public TimelineItem(String id) {
		super(id, KIND);
	}

	public static class Notification {
	}

	public static class MenuItem {

	}


}