package com.example.petsapplication.controller.exceptionhandler;

import com.example.petsapplication.exception.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<Object> handleHttpClientErrorException(
            HttpClientErrorException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now());
        body.put("message", ex.getMessage());

        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(body);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<Object> handleNullPointerException(
            NullPointerException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now());
        body.put("message", ex.getMessage());

        return ResponseEntity
                .status(NOT_FOUND)
                .body(body);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<Object> EntityNotFoundException(
            EntityNotFoundException ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now());
        body.put("message", ex.getMessage());

        return ResponseEntity
                .status(NOT_FOUND)
                .body(body);
    }
}
