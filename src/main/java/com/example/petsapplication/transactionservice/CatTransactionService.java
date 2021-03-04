package com.example.petsapplication.transactionservice;

import com.example.petsapplication.dto.CatDTO;
import com.example.petsapplication.dto.CreateCatDTO;
import com.example.petsapplication.entity.Owner;

public interface CatTransactionService extends RollbackTransactionService  {

    CatDTO save(CreateCatDTO catDTO, Owner owner);

}
