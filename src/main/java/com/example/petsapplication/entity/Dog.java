package com.example.petsapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Dog extends Pet {

    private String breed;

    @Builder(builderMethodName = "dogBuilder")
    public Dog(
            long id,
            String name,
            double age,
            Owner owner,
            String breed) {
        super(id, name, age, owner);
        this.breed = breed;
    }
}
