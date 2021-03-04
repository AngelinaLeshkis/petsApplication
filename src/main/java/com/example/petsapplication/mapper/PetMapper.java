package com.example.petsapplication.mapper;

import com.example.petsapplication.dto.CatDTO;
import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.dto.DogDTO;
import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class PetMapper {

    public static DogDTO toDogDto(Dog dog) {
        return DogDTO.builder()
                .id(dog.getId())
                .name(dog.getName())
                .age(dog.getAge())
                .ownerId(dog.getOwner().getId())
                .breed(dog.getBreed())
                .build();
    }

    public static CatDTO toCatDto(Cat cat) {
        return CatDTO.builder()
                .id(cat.getId())
                .name(cat.getName())
                .age(cat.getAge())
                .ownerId(cat.getOwner().getId())
                .view(cat.getView())
                .build();
    }

    public static CatDTO toCatDto(CreateCatDTO catDTO, Owner owner) {
        return CatDTO.builder()
                .name(catDTO.getName())
                .age(catDTO.getAge())
                .ownerId(owner.getId())
                .view(catDTO.getView())
                .build();
    }

    public static DogDTO toDogDto(CreateDogDTO dogDTO, Owner owner) {
        return DogDTO.builder()
                .name(dogDTO.getName())
                .age(dogDTO.getAge())
                .ownerId(owner.getId())
                .breed(dogDTO.getBreed())
                .build();
    }

}
