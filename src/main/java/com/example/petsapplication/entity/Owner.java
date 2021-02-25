package com.example.petsapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Owner {

    private long id;

    private String name;

    private List<Pet> pets;
}
