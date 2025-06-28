package com.myblog.service;


import com.myblog.result.Result;

public interface CaptchaService {
    Result sendCaptcha(String email);
    boolean verifyCaptcha(String email, String code);
}