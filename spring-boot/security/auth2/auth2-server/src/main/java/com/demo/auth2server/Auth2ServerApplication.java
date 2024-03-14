package com.demo.auth2server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
public class Auth2ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Auth2ServerApplication.class, args);
    }

}
