package com.example.petsapplication.repository;

import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.dto.DogDTO;
import com.example.petsapplication.entity.Owner;

import java.util.List;

public interface DogRepository {

    List<DogDTO> findAll();

    DogDTO save(CreateDogDTO dogDTO, Owner owner);

    void delete(long id);
}
