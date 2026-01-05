package com.example.myfitness.Exception;

public class MyFitnessUnAuthException extends MyFitnessException {
    public MyFitnessUnAuthException() {
        super("Unauthorized access");
    }

    public MyFitnessUnAuthException(String message) {
        super(message);
    }
}

