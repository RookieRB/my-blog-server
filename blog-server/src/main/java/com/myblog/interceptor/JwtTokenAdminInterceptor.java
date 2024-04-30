package com.myblog.interceptor;

import com.myblog.constant.JwtClaimsConstant;
import com.myblog.context.BaseContext;
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
@Slf4j
@Component
public class JwtTokenAdminInterceptor implements HandlerInterceptor {
    @Autowired
     private JwtProperties jwtProperties;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 判断当前拦截到的是Controller的方法还是其他资源
        if (!(handler instanceof HandlerMethod)) {
            //当前拦截到的不是动态方法，直接放行
            return true;
        }
        // 请求头中获取token
        String token = request.getHeader(jwtProperties.getUserTokenName());


        // 校验token
        try{
            log.info("jwt校验:{}", token);
            Claims claims = JwtUtil.parseJWT(jwtProperties.getUserSecretKey(), token);
            Long userId = Long.valueOf(claims.get(JwtClaimsConstant.USER_ID).toString());
            log.info("当前用户id：", userId);
            BaseContext.setCurrentId(userId);
            return true;
        } catch (Exception e){
            // 不通过，响应401状态码
            response.setStatus(401);
            return false;
        }
    }
}
