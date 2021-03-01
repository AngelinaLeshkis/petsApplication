package com.example.petsapplication.service.serviceimpl;

import com.example.petsapplication.pojo.AuthenticationInfo;
import com.example.petsapplication.service.HttpHeadersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HttpHeadersImpl implements HttpHeadersService {

    private final AuthenticationInfo authenticationInfo;

    @Override
    public HttpHeaders createHeaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        String authInformation = authenticationInfo.getAuthenticationInfo();
        httpHeaders.set("Authorization", authInformation);
        return httpHeaders;
    }
}
