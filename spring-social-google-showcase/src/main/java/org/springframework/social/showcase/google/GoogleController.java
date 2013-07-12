package org.springframework.social.showcase.google;

import org.springframework.social.connect.*;
import org.springframework.social.google.api.Google;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

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



}
