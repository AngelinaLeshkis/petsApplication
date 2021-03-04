package com.example.petsapplication.dto;

import com.example.petsapplication.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GeneralResponseDTO {

    private List<Owner> owners;
    private List<CatDTO> cats;
    private List<DogDTO> dogs;

}
