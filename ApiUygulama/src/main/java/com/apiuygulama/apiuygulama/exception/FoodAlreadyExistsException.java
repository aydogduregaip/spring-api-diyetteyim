package com.apiuygulama.apiuygulama.exception;

public class FoodAlreadyExistsException extends RuntimeException{
    public FoodAlreadyExistsException(String msg){
        super(msg);
    }
}
