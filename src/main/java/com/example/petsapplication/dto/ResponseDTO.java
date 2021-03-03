package com.example.petsapplication.dto;

import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDTO {

    private Owner owner;
    private Cat cat;
    private Dog dog;
}
