package com.minsait.onesait.microservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@EnableAutoConfiguration
@ComponentScan(basePackages = { "com.minsait.onesait" })
public class MicroserviceApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(MicroserviceApplication.class, args);
	}

}
