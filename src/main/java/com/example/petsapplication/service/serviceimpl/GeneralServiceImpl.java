package com.example.petsapplication.service.serviceimpl;

import com.example.petsapplication.dto.CatDTO;
import com.example.petsapplication.dto.DogDTO;
import com.example.petsapplication.dto.GeneralResponseDTO;
import com.example.petsapplication.dto.PostRequestDTO;
import com.example.petsapplication.dto.ResponseDTO;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.exception.RequestErrorException;
import com.example.petsapplication.repository.CatRepository;
import com.example.petsapplication.repository.DogRepository;
import com.example.petsapplication.repository.OwnerRepository;
import com.example.petsapplication.service.GeneralService;
import com.example.petsapplication.transactionservice.CatTransactionService;
import com.example.petsapplication.transactionservice.DogTransactionService;
import com.example.petsapplication.transactionservice.OwnerTransactionService;
import com.example.petsapplication.transactionservice.RollbackTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.example.petsapplication.mapper.OwnerMapper.toOwnerDTO;
import static com.example.petsapplication.mapper.ResponseMapper.toGeneralResponseDTO;
import static com.example.petsapplication.mapper.ResponseMapper.toResponseDTO;
import static com.example.petsapplication.utils.CheckEntityExistence.getMapOfEntities;
import static com.example.petsapplication.utils.CheckEntityExistence.setMapOfEntities;
import static com.example.petsapplication.utils.ConstantValues.CAT_TRANSACTION;
import static com.example.petsapplication.utils.ConstantValues.DOG_TRANSACTION;
import static com.example.petsapplication.utils.ConstantValues.OWNER_TRANSACTION;

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
