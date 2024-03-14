package com.demo.SpringSecurityDB;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class ServiceTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceTestApplication.class, args);
	}

}
