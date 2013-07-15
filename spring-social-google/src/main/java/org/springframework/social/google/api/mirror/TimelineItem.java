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
	@JsonProperty
	private Notification notification = new Notification();
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

		public Builder setSpeakableText(String speakableText) {
			this.timelineItem.speakableText = speakableText;
			return this;
		}

		// convenience methods for each of the various MenuItemAction types
		public Builder addShareMenuItem() {
			return addMenuItem(new MenuItem(MenuItem.MenuItemAction.SHARE));
		}

		public Builder addReadAloudMenuItem() {
			return addReadAloudMenuItem(this.timelineItem.speakableText);
		}

		public Builder addReadAloudMenuItem(String speakableText) {
			timelineItem.speakableText = speakableText;
			Assert.hasText(this.timelineItem.speakableText, "you must specify 'speakableText' to have your card read aloud");
			return addMenuItem(new MenuItem(MenuItem.MenuItemAction.READ_ALOUD));
		}

		public Builder addVoiceCallMenuItem() {
			return addMenuItem(new MenuItem(MenuItem.MenuItemAction.VOICE_CALL));
		}

		public Builder addNavigateMenuItem(Location location) {
			timelineItem.location = location;
			Assert.notNull(timelineItem.location, "you must specify a timelineItem.location if you specify a NAVIGATE action.");
			return addMenuItem(new MenuItem(MenuItem.MenuItemAction.NAVIGATE));
		}

		/**
		 * @param idOfMenuItem
		 * 		  if the menu item action is {@link org.springframework.social.google.api.mirror.MenuItem.MenuItemAction#CUSTOM},
		 * 		  then when the user selects this menuItem, the API triggers a notification to your {@code callbackUrl} with the
		 * 		  {@code userActions.type} set to {@code CUSTOM} and the {@code userActions.payload} set to the ID of this menu
		 * 		  item.
		 */
		public Builder addCustomMenuItem(String idOfMenuItem) {
			return addMenuItem(new MenuItem(idOfMenuItem));
		}

		public Builder addReplyMenuItem() {
			return addMenuItem(new MenuItem(MenuItem.MenuItemAction.REPLY));
		}

		public Builder addReplyAllMenuItem() {
			return addMenuItem(new MenuItem(MenuItem.MenuItemAction.REPLY_ALL));
		}

		public Builder addTogglePinnedMenuItem() {
			return addMenuItem(new MenuItem(MenuItem.MenuItemAction.TOGGLE_PINNED));
		}

		public Builder addDeleteMenuItem() {
			return addMenuItem(new MenuItem(MenuItem.MenuItemAction.DELETE));
		}

		public Builder addMenuItem(MenuItem menuItem) {
			timelineItem.getMenuItems().add(menuItem);
			return this;
		}

		public Builder setMenuItems(List<MenuItem> menuItems) {
			timelineItem.menuItems = new ArrayList<MenuItem>();
			if (menuItems != null && menuItems.size() > 0){
				for (MenuItem menuItem : menuItems) {
					addMenuItem(menuItem);
				}
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
			return this ;
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

		public Builder setNotification(Notification notification1) {
			this.timelineItem.notification = notification1;
			return this;
		}

		public Builder setTimelineItem(TimelineItem timelineItem) {
			this.timelineItem = timelineItem;
			return this;
		}
	}


}