package com.example.petsapplication.repository;

import com.example.petsapplication.dto.CatDTO;
import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Owner;

import java.util.List;

public interface CatRepository {

    List<CatDTO> findAll();

    CatDTO save(CreateCatDTO catDTO, Owner owner);

    void delete(long id);
}
