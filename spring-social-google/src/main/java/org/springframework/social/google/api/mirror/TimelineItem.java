package org.springframework.social.google.api.mirror;

import com.fasterxml.jackson.annotation.*;
import org.springframework.util.Assert;

import java.util.*;

/**
 * Parent type for things which may be managed by a {@link Timeline timeline}, including <em>cards</em> and
 * <EM>bundles</EM> (which are aggregates of cards).
 *
 * @author Josh Long
 */
@JsonIgnoreProperties (ignoreUnknown = true)
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

	protected TimelineItem() {
	}


	private TimelineItem(List<MenuItem> menuItems, Location location,
			                      String html, List<String> htmlPages, String title, String text,
			                      List<Contact> recipients, Contact creator, Date displayTime, boolean bundleCover, String bundleId, String canonicalUrl, String sourceItemId) {
		this.sourceItemId = sourceItemId;
		this.creator = creator;
		this.title = title;
		this.menuItems = menuItems;
		this.displayTime = displayTime;
		this.bundleId = bundleId;
		this.canonicalUrl = canonicalUrl;
		this.bundleCover = bundleCover;
		this.text = text;
		if (recipients != null){
			this.recipients.addAll(recipients);
		}


		this.html = html;
		this.htmlPages = htmlPages;
		if (this.htmlPages != null && this.htmlPages.size() > 0){
			// it's an error to specify htmlPages (which act as cards in a bundle)
			// without specifying the html field which will be used as the bundle cover card.
			Assert.hasText(this.html);
		}

		this.location = location;


	}

	public List<MenuItem> getMenuItems() {
		return menuItems;
	}

	public List<Attachment> getAttachments() {
		return attachments;
	}

	public List<Contact> getRecipients() {
		return recipients;
	}

	public List<String> getHtmlPages() {
		return htmlPages;
	}

	public boolean isPinned() {
		return pinned;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public boolean isBundleCover() {
		return bundleCover;
	}

	public Location getLocation() {
		return location;
	}

	public Notification getNotification() {
		return notification;
	}

	public Contact getCreator() {
		return creator;
	}

	public Date getDisplayTime() {
		return displayTime;
	}

	public Date getCreated() {
		return created;
	}

	public Date getUpdated() {
		return updated;
	}

	public String getBundleId() {
		return bundleId;
	}

	public String getInReplyTo() {
		return inReplyTo;
	}

	public String getText() {
		return text;
	}

	public String getTitle() {
		return title;
	}

	public String getSourceItemId() {
		return sourceItemId;
	}

	public String getSpeakableText() {
		return speakableText;
	}

	public String getCanonicalUrl() {
		return canonicalUrl;
	}

	public String getSelfLink() {
		return selfLink;
	}

	public String getHtml() {
		return html;
	}

	public int getPinScore() {
		return pinScore;
	}

	public static class Builder {
		private TimelineItem timelineItem = new TimelineItem();

		public TimelineItem build() {
			if (timelineItem.htmlPages != null && timelineItem.htmlPages.size() > 0){
				Assert.hasText(timelineItem.html, "it is an error to specify 'htmlPages[]' " +
				                                  "and not specify the 'html' attribute.");
			}
			return this.timelineItem;
		}

		public Builder setRecipients(List<Contact> recipients) {
			timelineItem.recipients = recipients;
			return this;
		}

		public Builder addMenuItem(MenuItem menuItem) {
			timelineItem.getMenuItems().add(menuItem);
			return this;
		}

		public Builder setMenuItems(List<MenuItem> menuItems) {
			timelineItem.menuItems = new ArrayList<MenuItem>();
			if (menuItems != null && menuItems.size() > 0){
				timelineItem.menuItems.addAll(menuItems);
			}
			return this;

		}

		public Builder setLocation(Location location) {
			timelineItem.location = location;
			return this;
		}

		public Builder setHtml(String html) {
			timelineItem.html = html;
			return this;
		}

		public Builder setHtmlPages(List<String> htmlPages) {
			timelineItem.htmlPages = htmlPages;
			return this;
		}

		public Builder setTitle(String title) {
			timelineItem.title = title;
			return this;
		}

		public Builder setText(String text) {
			timelineItem.text = text;
			return this;
		}

		public Builder setCreator(Contact creator) {
			timelineItem.creator = creator;
			return this;
		}

		public Builder setDisplayTime(Date displayTime) {
			timelineItem.displayTime = displayTime;
			return this;
		}

		public Builder setBundleCover(boolean bundleCover) {
			timelineItem.bundleCover = bundleCover;
			return this;
		}

		public Builder setBundleId(String bundleId) {
			timelineItem.bundleId = bundleId;
			return this;
		}

		public Builder setCanonicalUrl(String canonicalUrl) {
			timelineItem.canonicalUrl = canonicalUrl;
			return this;
		}

		public Builder setSourceItemId(String sourceItemId) {
			timelineItem.sourceItemId = sourceItemId;
			return this;
		}

		public Builder setTimelineItem(TimelineItem timelineItem) {
			this.timelineItem = timelineItem;
			return this;
		}
	}

	public static class Notification {
		// todo this should be an enum
		private String level = "DEFAULT";
		private Date deliveryTime;

		public Notification(String level, Date deliveryTime) {
			this.level = level;
			this.deliveryTime = deliveryTime;
		}

		protected Notification() {
		}

		public String getLevel() {
			return this.level;
		}

		public Date getDeliveryTime() {
			return this.deliveryTime;
		}
	}

	public static class MenuItem {
		private String id;
		private List<MenuItemValue> values = new ArrayList<MenuItemValue>();
		/* Built-in actions:
REPLY - Initiate a reply to the timeline item using the voice recording UI. The creator attribute must be set in the timeline item for this menu to be available.
REPLY_ALL - Same behavior as REPLY. The original timeline item's recipients will be added to the reply item.
DELETE - Delete the timeline item.
SHARE - Share the timeline item with the available contacts.
READ_ALOUD - Read the timeline item's speakableText aloud; if this field is not set, read the text field; if none of those fields are set, this menu item is ignored.
VOICE_CALL - Initiate a phone call using the timeline item's creator.phone_number attribute as recipient.
NAVIGATE - Navigate to the timeline item's location.
TOGGLE_PINNED - Toggle the isPinned state of the timeline item.*/
		private String action = "CUSTOM";// todo this should be an enum!!
		private boolean removeWhenSelected;

		public String getId() {
			return id;
		}

		public String getAction() {
			return action;
		}

		private List<MenuItemValue> getValues() {
			return this.values;
		}

		public boolean isRemoveWhenSelected() {
			return this.removeWhenSelected;
		}

		public static class MenuItemValue {
			private String displayName;
			private String iconUrl;
			private String state = "DEFAULT"; // todo this needs to be an enum!

			protected MenuItemValue() {
			}

			public MenuItemValue(String state, String iconUrl, String displayName) {
				this.state = state;
				this.iconUrl = iconUrl;
				this.displayName = displayName;
			}
		}
	}
}