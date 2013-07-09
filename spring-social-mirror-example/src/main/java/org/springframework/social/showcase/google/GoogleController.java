package org.springframework.social.showcase.google;

import org.springframework.security.core.Authentication;
import org.springframework.social.connect.*;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.mirror.*;
import org.springframework.social.google.api.plus.PlusOperations;
import org.springframework.social.google.api.userinfo.GoogleUserProfile;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@Controller
@RequestMapping ("/google")
public class GoogleController {

	@Inject private Google googleTemplate;

	@Inject private ConnectionRepository connectionRepository;

	@RequestMapping
	public String home(Model model) {
		Connection<Google> connection = connectionRepository.findPrimaryConnection(Google.class);
		if (connection == null){
			return "redirect:/connect/google";
		}
		model.addAttribute("profile", connection.getApi().userOperations().getUserProfile());
		return "google/profile";
	}

	@RequestMapping("/plus")
	public @ResponseBody Object  plus(Authentication authentication, Model model ) {
		 PlusOperations plusTemplate = googleTemplate.plusOperations();
		String accessToken = googleTemplate.getAccessToken();
		System.out.println( "access token: "+ accessToken);

//		GoogleUserProfile googleUserProfile = googleTemplate.userOperations().getUserProfile();
//		System.out.println(googleUserProfile.toString());

		TimelineOperations timelineOperations = googleTemplate.mirrorOperations().timelineOperations();
		List<TimelineItem> timeLineItems = timelineOperations.getTimelineItems();
	 	System.out.println(timeLineItems);
//		googleTemplate.mirrorOperations(). timelineOperations().getTimelineItems();

		return null ;

	}

}
