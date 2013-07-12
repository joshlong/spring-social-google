package org.springframework.social.showcase.google.glass;

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
		MirrorOperations mirrorOperations = this.google.mirrorOperations();
		return mirrorOperations.timelineOperations();
	}

	@RequestMapping ("/timelines")
	public String timelines() throws Throwable {
//		TimelineItem timelineItem = new TimelineItem.Builder()
//				  .setText("Hello world from card #"+ System.currentTimeMillis()).build() ;
//		timelineOperations().insertCard( timelineItem );
		return "redirect:/home";
	}
}
