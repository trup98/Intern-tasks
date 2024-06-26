package com.fullstacksecurity.backend.config.swagger;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;

@Configuration
public class SwaggerConfig {
  @Bean
  public OpenAPI api() {
    return new OpenAPI().info(
        new Info()
          .title("Back-End")
          .description("Back-End for react-Js Full stack with Security")
          .version("0.0.1"))
      .schemaRequirement(
        HttpHeaders.AUTHORIZATION,
        new SecurityScheme()
          .type(SecurityScheme.Type.HTTP)
          .scheme("bearer")
          .description("<b>Jwt Token Coming From Sign in API</b>")
          .name(HttpHeaders.AUTHORIZATION))
      .addSecurityItem(
        new SecurityRequirement()
          .addList(HttpHeaders.AUTHORIZATION, HttpHeaders.AUTHORIZATION));
  }
}
