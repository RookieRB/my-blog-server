package com.myblog.controller.user;

import com.myblog.constant.MessageConstant;
import com.myblog.dto.UserLoginDTO;
import com.myblog.entity.User;
import com.myblog.exception.SendEmailCodeFailedException;
import com.myblog.properties.JwtProperties;
import com.myblog.result.Result;
import com.myblog.service.UserService;
import com.myblog.utils.CodeGeneratorUtil;
import com.myblog.utils.JwtUtil;
import com.myblog.utils.MailUtils;
import com.myblog.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.myblog.constant.JwtClaimsConstant;

import javax.mail.MessagingException;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/user/user")
@RestController
@Api(tags = "C端用户相关接口")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private MailUtils mailUtils;

    @ApiOperation("界面登录")
    @PostMapping("/login")
    public Result<UserLoginVO> login(@RequestBody UserLoginDTO userinfo) {

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
                .userLevel(currentUser.getLevel())
                .build();
        return Result.success(userLoginVO);
    }


    @ApiOperation("发送邮箱验证码")
    @GetMapping(value = "sendEmail/{email}")
    public Result<Object> sendCode(@PathVariable String email) {


        try {
            mailUtils.snedSimpleMail(email,"邮箱验证码", CodeGeneratorUtil.generateCode(6));
        } catch (Exception e) {

            throw new SendEmailCodeFailedException(MessageConstant.SEND_EMAIL_CODE_FAILED);
        }
        return Result.success("验证码发送成功");
    }

    @ApiOperation("用户注册")
    @PostMapping(value = "register")
    public Result<String> register(@RequestBody User user) {

    }
}