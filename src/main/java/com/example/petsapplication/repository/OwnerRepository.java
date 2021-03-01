package com.example.petsapplication.repository;

import com.example.petsapplication.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface OwnerRepository {

    List<Owner> findAll();

    Optional<Owner> save(Owner owner);

    void delete(long id);

}
