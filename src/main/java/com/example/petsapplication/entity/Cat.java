package com.example.petsapplication.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Cat extends Pet {

    private String view;

    @Builder(builderMethodName = "catBuilder")
    public Cat(
            long id,
            String name,
            double age,
            Owner owner,
            String view) {
        super(id, name, age, owner);
        this.view = view;
    }

}
