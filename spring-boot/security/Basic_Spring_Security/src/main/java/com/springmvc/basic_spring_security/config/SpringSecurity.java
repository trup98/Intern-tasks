package com.springmvc.basic_spring_security.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


@Configuration
@EnableWebSecurity
public class SpringSecurity extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
//              By Default the csrf token is Enable for browsers we are sending request from postman So we have to Disable it
        csrf().disable().
//              We have to send csrf token from postman header if we use this
//              csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()).
//              Check Request to authorized
        authorizeRequests().
//              match any request came with public url and give permission to it without any username ot password
//              antMatchers("/public/**").permitAll()
//              Can Access public URL with Normal role
//              ADMIN can ACCESS both URLS public and users BUT NORMAL user can ACCESS only public URL
        antMatchers("/public/**").hasAnyRole("NORMAL", "ADMIN").
//              Can Access Users URl with ADMIN role
        antMatchers("/users/**").hasRole("ADMIN").
                anyRequest().
                authenticated().
                and().
                httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication().withUser("user").password(this.passwordEncoder().encode("user")).roles("NORMAL");
        auth.inMemoryAuthentication().withUser("admin").password(this.passwordEncoder().encode("admin")).roles("ADMIN");
    }

    @Bean
    public PasswordEncoder passwordEncoder()
    {
        return new BCryptPasswordEncoder(10);
    }
}
