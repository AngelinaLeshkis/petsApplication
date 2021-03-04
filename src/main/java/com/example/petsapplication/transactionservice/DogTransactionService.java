package com.example.petsapplication.transactionservice;

import com.example.petsapplication.dto.CreateDogDTO;
import com.example.petsapplication.dto.DogDTO;
import com.example.petsapplication.entity.Owner;

public interface DogTransactionService extends RollbackTransactionService {

    DogDTO save(CreateDogDTO dogDTO, Owner owner);

}
