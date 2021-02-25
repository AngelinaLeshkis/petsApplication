package com.example.petsapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public abstract class Pet {

    private long id;

    private String name;

    private double age;

    private Owner owner;
}
