package com.example.petsapplication.service.serviceimpl;

import com.example.petsapplication.dto.GeneralResponseDTO;
import com.example.petsapplication.dto.PostRequestDTO;
import com.example.petsapplication.dto.ResponseDTO;
import com.example.petsapplication.entity.Cat;
import com.example.petsapplication.entity.Dog;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.repository.CatRepository;
import com.example.petsapplication.repository.DogRepository;
import com.example.petsapplication.repository.OwnerRepository;
import com.example.petsapplication.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.example.petsapplication.mapper.ResponseMapper.toGeneralResponseDTO;
import static com.example.petsapplication.mapper.ResponseMapper.toResponseDTO;
import static org.springframework.http.HttpStatus.CREATED;
import static utils.CheckEntityExistence.getMapOfEntities;

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
    public ResponseDTO save(PostRequestDTO postRequestDTO) {
        Owner savedOwner = ownerRepository.save(postRequestDTO.getOwner());
        Dog savedDog = dogRepository.save(postRequestDTO.getDog(), savedOwner);
        Cat savedCat = catRepository.save(postRequestDTO.getCat(), savedOwner);

        List<Object> createdEntities = new ArrayList<>();
        for (Map.Entry<Object, HttpStatus> entry : getMapOfEntities().entrySet()) {
            while (entry.getValue().equals(CREATED)) {
                createdEntities.add(entry.getKey());
            }
        }

        if (createdEntities.size() != 3) {
            delete(createdEntities);
        }

        return toResponseDTO(savedOwner, savedDog, savedCat);
    }

    private void delete(List<Object> createdEntities) {
        for (Object createdObject : createdEntities) {
            if (createdObject instanceof Owner) {
                ownerRepository.delete(((Owner) createdObject).getId());
            } else if (createdObject instanceof Dog) {
                dogRepository.delete(((Dog) createdObject).getId());
            }
        }
    }

}
