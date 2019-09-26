package com.minsait.onesait.microservice.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;

@EnableOAuth2Client
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	private OAuth2ClientContext oauth2ClientContext;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// http.formLogin().loginPage("/login.html").loginProcessingUrl("/login").permitAll();

		http.csrf().disable();
		http.logout();
		http.authorizeRequests().antMatchers("/login**", "/**/*.css", "/img/**", "/third-party/**", "/").permitAll();
		http.authorizeRequests().antMatchers("/health/", "/info", "/metrics", "/trace", "/logfile").permitAll();
		http.authorizeRequests().regexMatchers("^/swagger.*", "^/v2/api-docs.*").permitAll();
		http.exceptionHandling().authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/"));

	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**", "/webjars/**");
	}

}
