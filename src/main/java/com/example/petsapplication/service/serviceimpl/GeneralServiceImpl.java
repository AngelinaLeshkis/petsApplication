package com.example.petsapplication.service.serviceimpl;

import com.example.petsapplication.dto.GeneralResponseDTO;
import com.example.petsapplication.repository.CatRepository;
import com.example.petsapplication.repository.DogRepository;
import com.example.petsapplication.repository.OwnerRepository;
import com.example.petsapplication.service.GeneralService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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

}
