package com.springboot.springkeyclock.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.OAuth2AccessToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private OAuth2AuthorizedClientService authorizedClientService;

    @GetMapping("/home")
    public String hello(OAuth2AuthenticationToken token) {
        OAuth2AuthorizedClient client = this.authorizedClientService.loadAuthorizedClient(
                token.getAuthorizedClientRegistrationId(),
                token.getName());
        OAuth2AccessToken accessToken = client.getAccessToken();
        System.out.println("accessToken ============================================== " + accessToken);
        System.out.println("accessTokenValue::::::::::::::::::::::::::::::::::::" + accessToken.getTokenValue());
        return "Welcome From Key clock !";
    }

}
