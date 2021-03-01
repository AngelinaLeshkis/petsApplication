package com.example.petsapplication.service.serviceimpl;

import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.dto.GeneralResponseDTO;
import com.example.petsapplication.dto.PostRequestDTO;
import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.exception.EntityNotFoundException;
import com.example.petsapplication.repository.CatRepository;
import com.example.petsapplication.repository.DogRepository;
import com.example.petsapplication.repository.OwnerRepository;
import com.example.petsapplication.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import static com.example.petsapplication.mapper.OwnerMapper.toOwner;
import static com.example.petsapplication.mapper.OwnerMapper.toOwnerDTO;
import static com.example.petsapplication.mapper.PetMapper.toDog;
import static com.example.petsapplication.mapper.ResponseMapper.toGeneralResponseDTO;

@Service
@RequiredArgsConstructor
public class GeneralServiceImpl implements GeneralService {

    private final OwnerRepository ownerRepository;
    private final DogRepository dogRepository;
    private final CatRepository catRepository;

    @Override
    public GeneralResponseDTO getAll() {
        return toGeneralResponseDTO(ownerRepository.findAll(),
                catRepository.findAll(), dogRepository.findAll());
    }

    @Override
    public PostRequestDTO save(PostRequestDTO postRequestDTO) {
        Owner savedOwner = ownerRepository.save(toOwner(postRequestDTO.getOwner()))
                .orElseThrow(() -> new EntityNotFoundException(Owner.class));

        CreateDogDTO savedDog = dogRepository.save(postRequestDTO.getDog(), savedOwner)
                .orElseThrow(() -> {
                    ownerRepository.delete(savedOwner.getId());
                    return new EntityNotFoundException(Dog.class);
                });

        CreateCatDTO savedCat = catRepository.save(postRequestDTO.getCat(), savedOwner)
                .orElseThrow(() -> {
                    Dog dog = toDog(savedDog, savedOwner);
                    ownerRepository.delete(savedOwner.getId());
                    dogRepository.delete(dog.getId());
                    return new EntityNotFoundException(Cat.class);
                });

        return PostRequestDTO.builder()
                .cat(savedCat)
                .dog(savedDog)
                .owner(toOwnerDTO(savedOwner))
                .build();
    }

}
