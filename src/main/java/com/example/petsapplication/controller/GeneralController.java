package com.example.petsapplication.controller;

import com.example.petsapplication.pojo.AuthenticationInfo;
import com.example.petsapplication.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/owners")
public class GeneralController {

    private final GeneralService generalService;
    private final AuthenticationInfo authenticationInfo;

    @GetMapping
    public ResponseEntity<List> getAll(@RequestHeader("Authorization") String authInformation) {
        authenticationInfo.setAuthenticationInfo(authInformation);
        return generalService.getAll(authenticationInfo);
    }
}
