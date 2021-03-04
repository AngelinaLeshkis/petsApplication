package com.example.petsapplication.utils;

import com.example.petsapplication.transactionservice.RollbackTransactionService;
import lombok.NoArgsConstructor;

import java.util.Map;
import java.util.TreeMap;

import static lombok.AccessLevel.PRIVATE;

@NoArgsConstructor(access = PRIVATE)
public final class CheckEntityExistence {

    private static Map<String, RollbackTransactionService> mapOfEntities = new TreeMap<>();

    public static Map<String, RollbackTransactionService> getMapOfEntities() {
        return mapOfEntities;
    }

    public static void setMapOfEntities(String value,
                                        RollbackTransactionService transactionService) {
        mapOfEntities.put(value, transactionService);
    }

}
