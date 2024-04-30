package com.myblog.controller.user;

import com.myblog.dto.UserLoginDTO;
import com.myblog.entity.User;
import com.myblog.properties.JwtProperties;
import com.myblog.result.Result;
import com.myblog.service.UserService;
import com.myblog.utils.JwtUtil;
import com.myblog.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.myblog.constant.JwtClaimsConstant;

import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user/user")
@RestController
@Api(tags = "C端用户相关接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;

    @ApiOperation("界面登录")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userinfo){

       User currentUser = userService.login(userinfo);
        //登录成功后，生成jwt令牌
        Map<String, Object> claims = new HashMap<>();
        claims.put(JwtClaimsConstant.USER_ID, currentUser.getId());
        String token = JwtUtil.createJWT(
                jwtProperties.getUserSecretKey(),
                jwtProperties.getUserTtl(),
                claims);


       UserLoginVO userLoginVO = UserLoginVO.builder()
               .id(currentUser.getId())
               .username(currentUser.getUserName())
               .token(token)
               .userImg(currentUser.getImgUrl())
               .build();
       return Result.success(userLoginVO);
    }

}
