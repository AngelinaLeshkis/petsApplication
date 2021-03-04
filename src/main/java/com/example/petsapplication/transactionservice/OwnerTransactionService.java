package com.example.petsapplication.transactionservice;

import com.example.petsapplication.dto.CreateOwnerDTO;
import com.example.petsapplication.entity.Owner;

public interface OwnerTransactionService extends RollbackTransactionService {

    Owner save(CreateOwnerDTO ownerDTO);
}
