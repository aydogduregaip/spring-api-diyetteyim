package com.apiuygulama.apiuygulama.exception;

public class FoodTypeNotFoundException extends RuntimeException{
    public FoodTypeNotFoundException(String msg){
        super(msg);
    }
}
