package com.apiuygulama.apiuygulama.exception;

public class ExerciseAlreadyExistsException extends RuntimeException {
    public ExerciseAlreadyExistsException(String msg){
        super(msg);
    }
}