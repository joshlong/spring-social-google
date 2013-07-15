package org.springframework.social.google.api.mirror.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.social.google.api.mirror.LocationOperations;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.util.*;

/**
 * Supports the Google Glass {@code location} collection.
 *
 * @author Josh Long
 */
public class LocationTemplate extends AbstractGoogleMirrorApiOperations implements LocationOperations {

	private final String subscriptions = "subscriptions";

	public LocationTemplate(RestTemplate restTemplate, boolean isAuthorized, boolean useDevelopmentProxiedCallbackUrls) {
		super(restTemplate, isAuthorized, useDevelopmentProxiedCallbackUrls);
	}

	/**
	 * This method subscribes the application to callbacks signalling when the location has changed.
	 * <p/>
	 * <pre>
	 * <code>{
	 *    "collection": "locations",
	 *    "userToken": "harold_penguin",
	 *    "verifyToken": "random_hash_to_verify_referer",
	 *    "callbackUrl": "https://example.com/notify/callback"
	 * }
	 * </code>
	 * </pre>
	 * <p/>
	 * Requests should be sent to {@code /mirror/v1/subscriptions} as an HTTP POST with an OAuth token, as {@code
	 * application/json}.
	 *
	 * @param callbackUrl
	 * 		  if {@link #useDevelopmentProxiedCallbackUrls} is true, then this URL will be not be called directly, and instead
	 * 		  routed through the Google Development SSL proxy as implemented in {@link #callbackUrl(String)}.
	 */

	@Override
	public void subscribeToLocationUpdates(String userToken, String verifyToken, String callbackUrl) {

		Map<String, String> locationSubscription = new HashMap<String, String>();
		locationSubscription.put("collection", "locations");
		locationSubscription.put("callbackUrl", callbackUrl(callbackUrl));
		locationSubscription.put("userToken", userToken);
		locationSubscription.put("verifyToken", verifyToken);

		debug("about to subscribe to '%s'.", this.subscriptionsUri());
		ResponseEntity<Map> mapOfReply = restTemplate.postForEntity(subscriptionsUri(), locationSubscription, Map.class);
		debug(mapOfReply.getBody());
		debug(mapOfReply.getStatusCode());
		debug(mapOfReply.getClass());


	}

	private String subscriptionsUri() {
		String url = this.getServiceUrl() + "/" + this.subscriptions;
		return URI.create(url).toString();
	}
}

