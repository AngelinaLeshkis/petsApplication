package com.example.petsapplication.mapper;

import com.example.petsapplication.dto.OwnerDTO;
import com.example.petsapplication.entity.Owner;
import lombok.NoArgsConstructor;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class OwnerMapper {

    public static OwnerDTO toOwnerDTO(Owner owner) {
        return OwnerDTO.builder()
                .id(owner.getId())
                .name(owner.getName())
                .build();
    }

    public static Owner toOwner(OwnerDTO ownerDTO) {
        return Owner.ownerBuilder()
                .id(ownerDTO.getId())
                .name(ownerDTO.getName())
                .build();
    }

}
