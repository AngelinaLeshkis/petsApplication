package com.example.petsapplication.service;

import com.example.petsapplication.pojo.AuthenticationInfo;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface GeneralService {

    ResponseEntity<List> getAll(AuthenticationInfo authenticationInfo);
}
