package com.example.SpringBoot.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.nio.file.Files;

@RestController
public class OpenApiController {

    @GetMapping("/openapi.yaml")
    public ResponseEntity<String> getOpenApiYaml() throws IOException {
        ClassPathResource resource = new ClassPathResource("openapi.yaml");
        String yamlContent = new String(Files.readAllBytes(resource.getFile().toPath()));
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, "application/x-yaml")
                .body(yamlContent);
    }
}
