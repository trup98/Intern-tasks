package com.learning.fullstack.backend.config;

import io.swagger.v3.oas.models.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;


@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI api() {
    return new OpenAPI().info(
        new Info().title("Back-End").description("Back-End for react-Js Full stack").version("0.0.1"))
      .servers(List.of(new Server().url("/")));
  }
}
