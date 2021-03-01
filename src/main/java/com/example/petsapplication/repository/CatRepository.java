package com.example.petsapplication.repository;

import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Owner;

import java.util.List;
import java.util.Optional;

public interface CatRepository {

    List<Cat> findAll();

    Optional<CreateCatDTO> save(CreateCatDTO catDTO, Owner owner);

    void delete(long id);
}
