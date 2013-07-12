package org.springframework.social.google.api.mirror;

import com.fasterxml.jackson.annotation.*;

import java.util.*;

/**
 * Parent type for things which may be managed by a {@link Timeline timeline}, including <em>cards</em> and
 * <EM>bundles</EM> (which are aggregates of cards).
 *
 * @author Josh Long
 */
@JsonIgnoreProperties(ignoreUnknown=true)
public class TimelineItem extends MirrorApiEntity {

	private final static String KIND = "mirror#timelineItem";
	private List<MenuItem> menuItems = new ArrayList<MenuItem>();
	private List<Attachment> attachments = new ArrayList<Attachment>();
	private List<Contact> recipients = new ArrayList<Contact>();
	private List<String> htmlPages = new ArrayList<String>();

	@JsonProperty ("isPinned")
	private boolean pinned;

	@JsonProperty ("isDeleted")
	private boolean deleted;

	@JsonProperty ("isBundleCover")
	private boolean bundleCover;

	private Location location;
	private Notification notification;
	private Contact creator;
	private Date displayTime;
	private Date created;
	private Date updated;
	private String bundleId;
	private String inReplyTo;
	private String text;
	private String title;
	private String sourceItemId;
	private String speakableText;
	private String canonicalUrl;
	private String selfLink;
	private String html;
	private int pinScore;

	public TimelineItem(String id) {
		super(id, KIND);
	}

	public static class Notification {
	}

	public static class MenuItem {

	}


}