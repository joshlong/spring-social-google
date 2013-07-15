package org.springframework.social.google.api.mirror;

/**
 *
 * Handles operations against the Google Glass LocationOperations 
 * @author Josh Long
 */
public interface LocationOperations {

	void subscribeToLocationUpdates ( String userToken, String verifyToken, String callbackUrl ) ;
}
        