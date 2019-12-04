/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.corp;

import java.net.URI;
import java.net.URISyntaxException;
import java.security.Principal;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;

@RestController
public class WebAppController {
//	@Autowired
//	private OAuth2RestOperations restTemplate;

	@GetMapping("/")
	public String index() throws URISyntaxException {
		ResponseEntity<String> result = new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		try {
			 URI uri = new URI("http://localhost:8080/resource");
			// result = restTemplate.getForEntity(uri, String.class);
			RestTemplate rest1 = new RestTemplate();
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_JSON);
			OAuth2AuthenticationDetails details = (OAuth2AuthenticationDetails) authentication.getDetails();
			headers.set("Authorization", "Bearer " + details.getTokenValue());

			Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
			String username = loggedInUser.getName();
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			result = rest1.exchange(uri, HttpMethod.GET, entity, String.class);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.getBody();
	}
}