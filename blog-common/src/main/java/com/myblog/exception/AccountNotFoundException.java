package com.myblog.exception;

public class AccountNotFoundException extends BaseExpection{
    public AccountNotFoundException(){

    }
    public AccountNotFoundException(String msg){
        super(msg);
    }
}
