package com.example.SpringBoot.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class AuthController {

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.client-id}")
    private String clientId;

    @Value("${keycloak.client-secret}")
    private String clientSecret;

    @Value("${keycloak.auth-server-url}")
    private String authServerUrl;

    @Value("${keycloak.redirect-uris}")
    private String redirectUri;

    @GetMapping("/callback")
    public ResponseEntity<String> callback(@RequestParam("code") String code) {
        
        String tokenEndpoint = String.format("%s/realms/%s/protocol/openid-connect/token", authServerUrl, realm);

        // Prepare the request for exchanging the authorization code for tokens
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        String body = String.format("grant_type=authorization_code&client_id=%s&client_secret=%s&code=%s&redirect_uri=%s",
        clientId, clientSecret, code, redirectUri);  // Use redirectUri instead of hardcoded localhost
    

        HttpEntity<String> entity = new HttpEntity<>(body, headers);
        
        // Send the request
        ResponseEntity<String> response = restTemplate.postForEntity(tokenEndpoint, entity, String.class);
        
        // Return the response (or handle it as needed)
        return response;
    }
}
