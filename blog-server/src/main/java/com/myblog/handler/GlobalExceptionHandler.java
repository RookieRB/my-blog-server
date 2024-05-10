package com.myblog.handler;

import com.myblog.exception.BaseException;
import com.myblog.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
    * 捕获并处理异常
    * */
    public Result exceptionHandler(BaseException baseExpection){
        log.error("异常信息: {}",baseExpection.getMessage());
        return Result.error(baseExpection.getMessage());
    }
}
