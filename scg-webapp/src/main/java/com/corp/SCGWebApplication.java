package com.corp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.oauth2.client.EnableOAuth2Sso;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableOAuth2Sso
@EnableDiscoveryClient
public class SCGWebApplication {

	public static void main(String[] args) {
		SpringApplication.run(SCGWebApplication.class, args);
	}

}
