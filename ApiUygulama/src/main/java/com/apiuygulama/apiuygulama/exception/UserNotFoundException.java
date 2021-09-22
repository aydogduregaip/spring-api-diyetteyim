package com.apiuygulama.apiuygulama.exception;

public class UserNotFoundException extends RuntimeException{
    public UserNotFoundException (String msg){
        super(msg);
    }
}
