package com.minsait.onesait.microservice.config;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLSession;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import com.minsait.onesait.microservice.error.ModelErrorHandler;

@Configuration
public class RestTemplateConfiguration {
	
	@Value("${security.basic.avoidSsl}")
	private boolean avoidSslCertificate;
	
	@Bean
	public RestTemplate initializeRestTemplate() {
	  if (avoidSslCertificate) {
			HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
	            public boolean verify(String hostname, SSLSession session) {
	                return true;
	            }
	        });
	  }
	  
	  final RestTemplate restTemplate = new RestTemplate(new HttpComponentsClientHttpRequestFactory());
	  restTemplate.setErrorHandler(new ModelErrorHandler());
	  
	  return restTemplate;
	  }

}
