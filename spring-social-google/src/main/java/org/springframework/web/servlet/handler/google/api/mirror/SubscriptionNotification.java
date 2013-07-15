package org.springframework.web.servlet.handler.google.api.mirror;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.*;

/**
 * A type that can be used to marshal the JSON payload for subscription notification callback {@code POST} requests that
 * contain payloads of the following form:
 * <p/>
 * <pre><code> {
 *    "collection": "timeline",
 *    "itemId": "3hidvm0xez6r8_dacdb3103b8b604_h8rpllg",
 *    "operation": "UPDATE",
 *    "userToken": "harold_penguin",
 *    "verifyToken": "random_hash_to_verify_referer",
 *    "userActions": [ { "type": "<TYPE>", "payload": "<PAYLOAD>" } ]
 * }
 * </code></pre>
 *
 * @author Josh Long
 */
public class SubscriptionNotification {
	private String collection;
	private String itemId;
	private String operation;
	private String userToken;
	private String verifyToken;
	@JsonProperty
	private List<UserAction> userActions = new ArrayList<UserAction>();

	public String getCollection() {
		return collection;
	}

	public String getItemId() {
		return itemId;
	}

	public String getOperation() {
		return operation;
	}

	public String getUserToken() {
		return userToken;
	}

	public String getVerifyToken() {
		return verifyToken;
	}

	public static class UserAction {
		private Objects type;
		private Object payload;

		public Object getPayload() {
			return this.payload;
		}

		public Objects getType() {
			return this.type;
		}
	}
}
