package com.example.petsapplication.service.serviceimpl;

import com.example.petsapplication.dto.CatDTO;
import com.example.petsapplication.dto.DogDTO;
import com.example.petsapplication.dto.PostRequestDTO;
import com.example.petsapplication.dto.ResponseDTO;
import com.example.petsapplication.entity.Owner;
import com.example.petsapplication.exception.RequestErrorException;
import com.example.petsapplication.service.PostService;
import com.example.petsapplication.transactionservice.CatTransactionService;
import com.example.petsapplication.transactionservice.DogTransactionService;
import com.example.petsapplication.transactionservice.OwnerTransactionService;
import com.example.petsapplication.transactionservice.RollbackTransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import static com.example.petsapplication.mapper.OwnerMapper.toOwnerDTO;
import static com.example.petsapplication.mapper.ResponseMapper.toResponseDTO;
import static com.example.petsapplication.utils.CheckEntityExistence.getMapOfEntities;
import static com.example.petsapplication.utils.CheckEntityExistence.setMapOfEntities;
import static com.example.petsapplication.utils.ConstantValues.CAT_TRANSACTION;
import static com.example.petsapplication.utils.ConstantValues.DOG_TRANSACTION;
import static com.example.petsapplication.utils.ConstantValues.OWNER_TRANSACTION;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final CatTransactionService catTransactionService;
    private final DogTransactionService dogTransactionService;
    private final OwnerTransactionService ownerTransactionService;

    @Override
    public ResponseDTO save(PostRequestDTO postRequestDTO) {
        try {
            Owner savedOwner = ownerTransactionService.save(postRequestDTO.getOwner());
            setMapOfEntities(OWNER_TRANSACTION, ownerTransactionService);
            DogDTO savedDog = dogTransactionService.save(postRequestDTO.getDog(), savedOwner);
            setMapOfEntities(CAT_TRANSACTION, dogTransactionService);
            CatDTO savedCat = catTransactionService.save(postRequestDTO.getCat(), savedOwner);
            setMapOfEntities(DOG_TRANSACTION, catTransactionService);
            return toResponseDTO(toOwnerDTO(savedOwner), savedDog, savedCat);
        } catch (HttpClientErrorException | HttpServerErrorException errorException) {
            getMapOfEntities().values().forEach(RollbackTransactionService::rollback);
            throw new RequestErrorException();
        }
    }
}
