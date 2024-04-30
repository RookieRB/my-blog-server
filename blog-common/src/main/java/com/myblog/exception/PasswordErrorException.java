package com.myblog.exception;

public class PasswordErrorException extends BaseExpection{
    public PasswordErrorException(){

    }
    public PasswordErrorException(String msg){
        super(msg);
    }
}
