package com.example.petsapplication.dto;

import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Dog;
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
    private List<Cat> cats;
    private List<Dog> dogs;

}
