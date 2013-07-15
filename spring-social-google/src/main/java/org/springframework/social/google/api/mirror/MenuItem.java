package org.springframework.social.google.api.mirror;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.util.Assert;

import java.util.*;

public class MenuItem {
	private String id;
	private List<MenuItemValue> values = new ArrayList<MenuItemValue>();
	private boolean removeWhenSelected;
	// default
	@JsonProperty
	private MenuItemAction action = MenuItemAction.CUSTOM;

	public MenuItem(MenuItemAction action) {
		this(action, false, null);
	}

	/* to support Jackson's JSON marshalling */
	protected MenuItem() {
	}

	/**
	 * Register a {@link MenuItemAction CUSTOM menu item action} that has an {@code id} that will be communicated to the
	 * {@code callbackUrl} when the ultimate request is made.
	 */
	public MenuItem(String id) {
		initialize(null, id, false);
	}

	public MenuItem(MenuItemAction action, String id) {
		initialize(action, id, false);
	}

	public MenuItem(MenuItemAction action, boolean removeWhenSelected, String id) {
		initialize(action, id, removeWhenSelected);
	}

	private void initialize(MenuItemAction action, String id, boolean removeWhenSelected) {
		this.id = id;
		this.action = action == null ? MenuItemAction.CUSTOM : action;
		if (MenuItemAction.CUSTOM.equals(this.action)){
			Assert.hasText(this.id, "you should specify an 'id' if you choose to add a CUSTOM menu item " +
			                        "as that will be used to communicate to the service at your application's 'callbackUrl' what the desired behavior should be.");
		}
		this.removeWhenSelected = removeWhenSelected;
	}

	public String getId() {
		return id;
	}

	private List<MenuItemValue> getValues() {
		return this.values;
	}

	public boolean isRemoveWhenSelected() {
		return this.removeWhenSelected;
	}

	public static enum MenuItemAction {
		/**
		 * Custom action set by the service. When the user selects this menuItem, the API triggers a notification to your
		 * callbackUrl with the userActions.type set to CUSTOM and the userActions.payload set to the ID of this menu item.
		 * This is the default value.
		 */
		CUSTOM,

		/**
		 * Initiate a reply to the timeline item using the voice recording UI. The creator attribute must be set in the
		 * timeline item for this menu to be available.
		 */
		REPLY,

		/** Same behavior as REPLY. The original timeline item's recipients will be added to the reply item. */
		REPLY_ALL,

		/** Delete the timeline item. */
		DELETE,

		/** Share the timeline item with the available contacts. */
		SHARE,

		/**
		 * Read the timeline item's speakableText aloud; if this field is not set, read the text field; if none of those
		 * fields are set, this menu item is ignored.
		 */
		READ_ALOUD,

		/** Initiate a phone call using the timeline item's creator.phone_number attribute as recipient. */
		VOICE_CALL,

		/** Navigate to the timeline item's location. */
		NAVIGATE,

		/**
		 * Toggle the isPinned state.  This will be reflected in the {@code isPinned} property when {@link TimelineItem
		 * timeline items} are read
		 */
		TOGGLE_PINNED
	}

	public static class MenuItemValue {
		private String displayName;
		private String iconUrl;
		@JsonProperty
		private MenuItemValueState state = MenuItemValueState.DEFAULT;

		protected MenuItemValue() {
		}

		public MenuItemValue(MenuItemValueState state, String iconUrl, String displayName) {
			this.state = state;
			this.iconUrl = iconUrl;
			this.displayName = displayName;
		}

		public String getDisplayName() {
			return displayName;
		}

		public String getIconUrl() {
			return iconUrl;
		}

		public MenuItemValueState getState() {
			return state;
		}

		public static enum MenuItemValueState {
			DEFAULT, PENDING, CONFIRMED
		}

	}
}