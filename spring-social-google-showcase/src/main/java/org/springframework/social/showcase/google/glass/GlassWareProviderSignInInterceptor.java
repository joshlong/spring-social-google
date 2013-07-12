package org.springframework.social.showcase.google.glass;

import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.social.connect.*;
import org.springframework.social.connect.web.ProviderSignInInterceptor;
import org.springframework.social.google.api.Google;
import org.springframework.util.MultiValueMap;
import org.springframework.web.context.request.WebRequest;

/**
 * @author Josh Long
 */
public class GlassWareProviderSignInInterceptor implements ProviderSignInInterceptor <Google>{

	@Override
	public void preSignIn( ConnectionFactory<Google> googleConnectionFactory,
                          MultiValueMap<String, String> stringStringMultiValueMap,
                          WebRequest webRequest) {
	}

	@Override
	public void postSignIn(Connection<Google> googleConnection, WebRequest webRequest) {
	/*	if (webRequest instanceof NativeWebRequest){
			NativeWebRequest nativeWebRequest = (NativeWebRequest) webRequest;
			HttpServletRequest httpServletRequest = nativeWebRequest.getNativeRequest(HttpServletRequest.class);
			HttpServletResponse httpServletResponse = nativeWebRequest.getNativeResponse(HttpServletResponse.class);
			Authentication authentication = this.rememberMeServices.autoLogin(httpServletRequest, httpServletResponse);
			if(null !=authentication ){
				this.rememberMeServices.loginSuccess( httpServletRequest, httpServletResponse, authentication);
			}
		}*/
	}
	public GlassWareProviderSignInInterceptor (RememberMeServices rememberMeServices ){
		this.rememberMeServices = rememberMeServices;
	}

	private RememberMeServices rememberMeServices ;
}
