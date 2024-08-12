package com.fullstacksecurity.backend;

import com.fullstacksecurity.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@RequiredArgsConstructor
public class BackEndApplication implements CommandLineRunner {

  private final UserService userService;


	public static void main(String[] args) {
		SpringApplication.run(BackEndApplication.class, args);
	}


  @Override
  public void run(String... args) throws Exception {
    userService.m2();

  }
}
