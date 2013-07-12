/*
 * Copyright 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.social.showcase;

import org.springframework.social.connect.ConnectionRepository;
import org.springframework.social.showcase.account.AccountRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.*;
import java.security.Principal;

@Controller
public class HomeController {

	@Inject private Provider<ConnectionRepository> connectionRepositoryProvider;
	@Inject private AccountRepository accountRepository;

	@RequestMapping ("/")
	public String home(Principal currentUser, Model model) {
		model.addAttribute(accountRepository.findAccountByUsername(currentUser.getName()));
		return "home";
	}

	@RequestMapping ("/connections")
	public String connections(Model model) {
		model.addAttribute("connectionsToProviders", connectionRepositoryProvider.get().findAllConnections());
		return "connections";
	}
}
