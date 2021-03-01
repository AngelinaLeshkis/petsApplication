package com.example.petsapplication.repository;

import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface DogRepository {

    List<Dog> findAll();

    Optional<CreateDogDTO> save(CreateDogDTO dogDTO, Owner owner);

    void delete(long id);
}
