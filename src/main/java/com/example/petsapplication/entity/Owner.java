package com.example.petsapplication.entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Owner {

    private long id;

    private String name;


    @Builder(builderMethodName = "ownerBuilder")
    public Owner(long id, String name) {
        this.id = id;
        this.name = name;
    }
}
