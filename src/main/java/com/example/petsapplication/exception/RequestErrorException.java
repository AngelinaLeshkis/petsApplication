package com.example.petsapplication.exception;

public class RequestErrorException extends RuntimeException {

    public RequestErrorException() {
        super("Such request can`t be saved!");
    }
}
