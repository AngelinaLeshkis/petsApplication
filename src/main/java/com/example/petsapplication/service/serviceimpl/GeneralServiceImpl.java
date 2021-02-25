package com.example.petsapplication.service.serviceimpl;

import com.example.petsapplication.pojo.AuthenticationInfo;
import com.example.petsapplication.repository.CatRepository;
import com.example.petsapplication.repository.DogRepository;
import com.example.petsapplication.repository.OwnerRepository;
import com.example.petsapplication.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.asList;
import static org.springframework.http.HttpStatus.OK;

@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {

    private final OwnerRepository ownerRepository;
    private final DogRepository dogRepository;
    private final CatRepository catRepository;

    @Override
    public ResponseEntity<List> getAll(AuthenticationInfo authenticationInfo) {
        return ResponseEntity
                .status(OK)
        .body(asList(ownerRepository.findAll(authenticationInfo), dogRepository.findAll(authenticationInfo),
                catRepository.findAll(authenticationInfo)));
    }

}
