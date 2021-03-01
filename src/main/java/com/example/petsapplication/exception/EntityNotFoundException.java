package com.example.petsapplication.exception;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Class cs) {
        super("Such " + cs + " not found!");
    }
}
