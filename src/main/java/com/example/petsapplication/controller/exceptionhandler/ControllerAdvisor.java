package com.example.petsapplication.controller.exceptionhandler;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.NotFound;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.LinkedHashMap;
import java.util.Map;

import static java.time.LocalTime.now;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.UNAUTHORIZED;

@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(Unauthorized.class)
    public ResponseEntity<Object> handleUnauthorizedException(
            Unauthorized ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now());
        body.put("message", ex.getMessage());

        return ResponseEntity
                .status(UNAUTHORIZED)
                .body(body);
    }

    @ExceptionHandler(NotFound.class)
    public ResponseEntity<Object> handleNotFoundException(
            NotFound ex) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("timestamp", now());
        body.put("message", ex.getMessage());

        return ResponseEntity
                .status(NOT_FOUND)
                .body(body);
    }
}
