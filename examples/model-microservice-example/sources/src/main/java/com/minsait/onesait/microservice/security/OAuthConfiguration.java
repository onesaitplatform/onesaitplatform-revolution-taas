package com.minsait.onesait.microservice.security;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.provider.token.RemoteTokenServices;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuthConfiguration {

	@Value("${security.oauth2.client.clientId}")
	private String CLIENT_ID;

	@Value("${security.oauth2.client.clientSecret}")
	private String CLIENT_SECRET;

	@Value("${security.oauth2.client.checkTokenEndpointUrl}")
	private String CHECK_TOKEN_ENDPOINT_URL;
	

	@Primary
	@Bean
	public RemoteTokenServices remoteTokenServices() {
		final RemoteTokenServices tokenServices = new RemoteTokenServices();
		tokenServices
				.setCheckTokenEndpointUrl(CHECK_TOKEN_ENDPOINT_URL);
		tokenServices.setClientId(CLIENT_ID);
		tokenServices.setClientSecret(CLIENT_SECRET);
		return tokenServices;
	}

}
