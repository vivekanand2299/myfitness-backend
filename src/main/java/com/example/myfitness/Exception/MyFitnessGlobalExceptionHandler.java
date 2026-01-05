package com.example.myfitness.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
public class MyFitnessGlobalExceptionHandler {

    @ExceptionHandler(MyFitnessUnAuthException.class)
    public ResponseEntity<?> handleUnAuth(MyFitnessUnAuthException ex) {
        return ResponseEntity
                .status(HttpStatus.UNAUTHORIZED)
                .body(Map.of(
                        "status", 401,
                        "error", "UNAUTHORIZED",
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(MyFitnessException.class)
    public ResponseEntity<?> handleBase(MyFitnessException ex) {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(Map.of(
                        "status", 400,
                        "error", "BAD_REQUEST",
                        "message", ex.getMessage()
                ));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleAll(Exception ex) {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(Map.of(
                        "status", 500,
                        "error", "INTERNAL_ERROR",
                        "message", "Something went wrong"
                ));
    }
}

