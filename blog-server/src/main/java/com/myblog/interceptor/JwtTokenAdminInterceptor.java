package com.myblog.interceptor;

import com.myblog.constant.JwtClaimsConstant;
import com.myblog.constant.MessageConstant;
import com.myblog.context.BaseContext;
import com.myblog.exception.LoginInfoExpireException;
import com.myblog.properties.JwtProperties;
import com.myblog.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Slf4j
@Component
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
    private JwtProperties jwtProperties;


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是不是Controller的方法
        if(!(handler instanceof HandlerMethod)){
            // 如果不是，直接放行
            return true;
        }

        // 获取请求头当中的token
        String token = request.getHeader(jwtProperties.getUserTokenName());
        log.info("当前token{}",token);
        // 校验token
        try{

            Claims claims =  JwtUtil.parseJWT(jwtProperties.getUserSecretKey(),token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            Date exp = claims.getExpiration();

            if(exp.before(new Date())){
                // token失效
                throw new LoginInfoExpireException(MessageConstant.LOGIN_INFO_EXPIRE);
            }

            BaseContext.setCurrentId(userId);
            log.info("验证成功，当前用户{}",userId);
            return  true;
        }catch (Exception e){
            response.setStatus(401);
            return false;
        }

    }
}
