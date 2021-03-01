package com.example.petsapplication.controller;

import com.example.petsapplication.dto.GeneralResponseDTO;
import com.example.petsapplication.dto.PostRequestDTO;
import com.example.petsapplication.pojo.AuthenticationInfo;
import com.example.petsapplication.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/entities")
public class GeneralController {

    private final GeneralService generalService;
    private final AuthenticationInfo authenticationInfo;

    @GetMapping
    public ResponseEntity<GeneralResponseDTO> getAll(@RequestHeader("Authorization")
                                                             String authInformation) {
        authenticationInfo.setAuthenticationInfo(authInformation);
        return ResponseEntity.status(OK)
                .body(generalService.getAll());
    }

    @PostMapping
    public ResponseEntity<PostRequestDTO> save(@RequestHeader("Authorization") String authInformation,
                                               @RequestBody PostRequestDTO postRequestDTO) {
        authenticationInfo.setAuthenticationInfo(authInformation);
        return ResponseEntity.status(OK)
                .body(generalService.save(postRequestDTO));
    }


}
