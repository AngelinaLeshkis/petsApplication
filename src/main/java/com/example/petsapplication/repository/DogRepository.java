package com.example.petsapplication.repository;

import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;

import java.util.List;

public interface DogRepository {

    List<Dog> findAll();

    Dog save(CreateDogDTO dogDTO, Owner owner);

    void delete(long id);
}
