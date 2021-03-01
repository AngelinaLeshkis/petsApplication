package com.example.petsapplication.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class Owner {

    private long id;

    private String name;

    private List<Pet> pets;

    @Builder(builderMethodName = "ownerBuilder")
    public Owner(long id, String name, List<Pet> pets) {
        this.id = id;
        this.name = name;
        this.pets = pets;
    }
}
