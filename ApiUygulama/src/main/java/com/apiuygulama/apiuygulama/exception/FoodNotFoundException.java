package com.apiuygulama.apiuygulama.exception;

public class FoodNotFoundException extends RuntimeException{
    public FoodNotFoundException (String msg){
        super(msg);
    }
}
