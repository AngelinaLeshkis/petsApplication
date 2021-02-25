package com.example.petsapplication.repository;

import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.pojo.AuthenticationInfo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

import static org.springframework.http.HttpMethod.GET;

@Component
@RequiredArgsConstructor
public class DogRepository {

    @Value("${backend.server.url}")
    private String backendServerUrl;
    private final RestTemplate restTemplate;

    public ResponseEntity<List> findAll(AuthenticationInfo authenticationInfo) {
        return restTemplate.exchange
                (backendServerUrl + "dogs", GET, new HttpEntity<Dog>(createHeaders(authenticationInfo)), List.class);
    }

    private HttpHeaders createHeaders(AuthenticationInfo authenticationInfo) {
        HttpHeaders httpHeaders = new HttpHeaders();
        String authInformation = authenticationInfo.getAuthenticationInfo();
        httpHeaders.set("Authorization", authInformation);
        return httpHeaders;
    }
}
