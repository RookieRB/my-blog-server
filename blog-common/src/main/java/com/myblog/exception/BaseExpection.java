package com.myblog.exception;

public class BaseExpection extends RuntimeException{
    public BaseExpection(){

    }
    public BaseExpection(String msg){
        super(msg);
    }
}
