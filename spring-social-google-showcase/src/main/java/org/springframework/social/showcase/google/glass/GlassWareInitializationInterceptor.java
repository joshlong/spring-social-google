package org.springframework.social.showcase.google.glass;

import org.springframework.social.connect.*;
import org.springframework.social.connect.web.ConnectInterceptor;
import org.springframework.social.google.api.Google;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

/**
 * Establishes certain subscriptions like the location and other events.
 *
 * @author Josh Long
 */
public class GlassWareInitializationInterceptor implements ConnectInterceptor<Google> {

	// check box in the setup flow that indicates whether the application can send an introduction card.

	private static final String SEND_INTRODUCTION_CARD = "glass.sendIntroductionCard";

	@Override
	public void preConnect(ConnectionFactory<Google> googleConnectionFactory, MultiValueMap<String, String> stringStringMultiValueMap, WebRequest webRequest) {

	}


	@Override
	public void postConnect(Connection<Google> googleConnection, WebRequest webRequest) {



	/*	if (webRequest instanceof NativeWebRequest){

			NativeWebRequest nativeWebRequest = (NativeWebRequest) webRequest;
			HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
			HttpServletResponse httpServletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
			this.persistentTokenBasedRememberMeServices.autoLogin(httpServletRequest, httpServletResponse);
		}*/


		// good things to do on the startup of a valid session with the Google Glass-authorized APIs:
		// - subscribe to changes in the user's device timeline (including registering a callback URL)
		// - install a 'contact' if one doesn't already exist that makes your Glassware a 'share with' destination.
		// - (optional) say hello to the client of the application by inserting a card into the user's device timeline


	}

}
