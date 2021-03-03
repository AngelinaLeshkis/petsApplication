package com.example.petsapplication.repository;

import com.example.petsapplication.dto.CreateOwnerDTO;
import com.example.petsapplication.entity.Owner;

import java.util.List;

public interface OwnerRepository {

    List<Owner> findAll();

    Owner save(CreateOwnerDTO owner);

    void delete(long id);

}
