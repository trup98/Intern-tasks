package com.springboot_jwt.config;

import com.springboot_jwt.filter.JwtFilter;
import com.springboot_jwt.service.CustomUserDetailService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableMethodSecurity
public class SpringSecurity {
  private final AuthenticationConfiguration authConfiguration;
  private final CrossOriginFilter crossOriginFilter;
  private final JwtFilter jwtFilter;

  @Bean
  public UserDetailsService userDetailsService() {
    return new CustomUserDetailService();
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
    http.cors().configurationSource(crossOriginFilter.corsConfigurationSource());
    return http.
      csrf().disable().
      authorizeHttpRequests()
      .antMatchers(AUTH_WHITELIST)
      .permitAll().
      and().
      sessionManagement().
      sessionCreationPolicy(SessionCreationPolicy.STATELESS).
      and().
      authenticationProvider(authenticationProvider()).
      addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class).
      build();
  }

  @Bean
  public AuthenticationProvider authenticationProvider() {
    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
    daoAuthenticationProvider.setUserDetailsService(userDetailsService());
    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
    return daoAuthenticationProvider;
  }

  @Bean
  public AuthenticationManager authenticationManager() throws Exception {
    return authConfiguration.getAuthenticationManager();
  }

  private static final String[] AUTH_WHITELIST = {
    // -- Swagger UI v3 (OpenAPI)
    "/v3/api-docs/**",
    "/swagger-ui/**",
    "/user/add",
    "/user/authenticate",
    "/user/refreshToken"
    // other public endpoints of your API may be appended to this array
  };

}
