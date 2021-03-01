package com.example.petsapplication.mapper;

import com.example.petsapplication.dto.GeneralResponseDTO;
import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;
import lombok.NoArgsConstructor;

import java.util.List;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class ResponseMapper {

    public static GeneralResponseDTO toGeneralResponseDTO(List<Owner> owners, List<Cat> cats,
                                                          List<Dog> dogs) {
        return GeneralResponseDTO.builder()
                .owners(owners)
                .cats(cats)
                .dogs(dogs)
                .build();
    }
}
