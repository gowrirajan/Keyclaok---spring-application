package com.example.SpringBoot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.beans.factory.annotation.Value;

@RestController
public class LoginController {

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.redirect-uris}")
    private String redirectUri;

    @GetMapping("/login")
    public ModelAndView login() {
        
        String authUrl = String.format("%s/realms/%s/protocol/openid-connect/auth?client_id=%s&redirect_uri=%s&response_type=code&scope=openid",
                authServerUrl, realm, clientId, redirectUri);

        return new ModelAndView("redirect:" + authUrl);
    }
}
