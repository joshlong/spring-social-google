package org.springframework.social.showcase.google.glass;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.logging.*;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.handler.google.api.mirror.SubscriptionNotification;

/**
 * Handles notifications into the application when the {@code location} or {@code timeline} change.
 *
 * @author Josh Long
 */
@Controller ("/notifications")
public class NotificationCallbackController {

	private Log log = LogFactory.getLog(getClass());

	@RequestMapping ("/subscriptions")
	public void handleSubscriptionCallbackNotification(HttpEntity<SubscriptionNotification> subscriptionNotification) {
		log.debug("Receiving subscription notification.");
		SubscriptionNotification notification = subscriptionNotification.getBody();
		log.debug(ToStringBuilder.reflectionToString(notification));


   }
}
