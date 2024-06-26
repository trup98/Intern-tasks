package com.fullstacksecurity.backend.security.web;

import com.fullstacksecurity.backend.security.jwt.JwtAuthenticationEntryPoint;
import com.fullstacksecurity.backend.security.jwt.JwtTokenFilter;
import com.fullstacksecurity.backend.security.jwt.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class WebSecurity {

  private final AuthenticationProvider authenticationProvider;
  private final CrossOriginFilter crossOriginFilter;
  private final JwtTokenProvider jwtTokenProvider;
  private final JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint;

  @Primary
  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    return http
      .csrf(AbstractHttpConfigurer::disable)
      .cors(configure -> configure.configurationSource(crossOriginFilter.corsConfigurationSource()))
      .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
      .authenticationProvider(authenticationProvider)
      .authorizeHttpRequests(auth -> auth
        .requestMatchers(this.getPublicUrls()).permitAll()
        .anyRequest().authenticated())
      .addFilterBefore(new JwtTokenFilter(jwtTokenProvider), UsernamePasswordAuthenticationFilter.class)
      .build();
  }

  private String[] getPublicUrls() {
    return new String[]{
      "/api/v1/auth/login",
      // for Swagger UI v2
      "/v2/api-docs",
      "/swagger-ui.html",
      "/swagger-resources",
      "/swagger-resources/**",
      "/configuration/ui",
      "/configuration/security",
      "/webjars/**",

      // for Swagger UI v3 (OpenAPI)
      "/v3/api-docs/**",
      "/swagger-ui/**"
    };
  }

}
