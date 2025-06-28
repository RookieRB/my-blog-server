package com.myblog.exception;

public class LoginInfoExpireException extends BaseException{
    public LoginInfoExpireException() {
    }

    public LoginInfoExpireException(String msg){
        super(msg);
    }
}
