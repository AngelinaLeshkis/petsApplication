package com.example.petsapplication.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CatDTO {

    private long id;
    private String name;
    private double age;
    private long ownerId;
    private String view;
}
