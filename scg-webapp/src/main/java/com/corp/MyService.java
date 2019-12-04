package com.corp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyService {
	@Autowired
	private OAuth2RestOperations restTemplate;

	public MyService() {

		
	}

}