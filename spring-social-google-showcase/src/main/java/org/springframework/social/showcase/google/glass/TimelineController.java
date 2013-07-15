package org.springframework.social.showcase.google.glass;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.mirror.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

/**
 * Controller to manipulate the Google Glass devices' timeline.
 *
 * @author Josh Long
 */
@Controller
public class TimelineController {
	@Inject
	private Google google;

	private TimelineOperations timelineOperations() {
		return google.mirrorOperations().timelineOperations();
	}

	@RequestMapping ("/timelines")
	public String timelines() throws Throwable {


		timelineOperations().clearTimeline();
		long currentTimeMillis = System.currentTimeMillis() ;

		 debug( timelineOperations().insertCard(
		  new TimelineItem.Builder()
				    .addShareMenuItem()
					 .addDeleteMenuItem()
					 .addReadAloudMenuItem("This is speakable text.")
				    .addTogglePinnedMenuItem()
				    .addCustomMenuItem("joshs_custom_menu_item_"+ currentTimeMillis  )
				    .setText("The text is plain text #" + currentTimeMillis )
					 .build()
		 ) );



		debug(timelineOperations().getTimelineItems());

		return "redirect:/google";
	}

	/* debug the contents of the object */
	private void debug(Object o) {
		System.out.println(ToStringBuilder.reflectionToString(o));
	}
}
