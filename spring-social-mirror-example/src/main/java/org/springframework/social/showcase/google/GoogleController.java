package org.springframework.social.showcase.google;

import org.springframework.http.*;
import org.springframework.social.connect.*;
import org.springframework.social.google.api.Google;
import org.springframework.social.google.api.drive.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

/** @author Josh Long */
@Controller
public class GoogleController {


	private final String googlePath = "google";

//	@Inject
	private Google googleTemplate;

//	@Inject
	private ConnectionRepository connectionRepository;

	@RequestMapping ("/drive-about")
	public ResponseEntity<?> drive() {
		DriveOperations driveOperations = this.googleTemplate.driveOperations();
		DriveAbout driveAbout = driveOperations.getAbout();
		return new ResponseEntity<DriveAbout>(driveAbout, null, HttpStatus.OK);
	}

	@RequestMapping (value = googlePath, method = RequestMethod.GET)
	public String home(Model model) {
		Connection<Google> connection = connectionRepository.findPrimaryConnection(Google.class);
		if (connection == null){
			return "redirect:/connect/" + googlePath;
		}
		model.addAttribute("profile", connection.getApi().userOperations().getUserProfile());
		return "/" + this.googlePath + "/profile";
	}

}
