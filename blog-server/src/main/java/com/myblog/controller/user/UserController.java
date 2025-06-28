package com.myblog.controller.user;

import com.myblog.constant.MessageConstant;
import com.myblog.dto.UserLoginDTO;
import com.myblog.dto.UserRegisterDTO;
import com.myblog.entity.User;
import com.myblog.exception.SendEmailCodeFailedException;
import com.myblog.properties.JwtProperties;
import com.myblog.result.Result;
import com.myblog.service.QrCodeService;
import com.myblog.service.UserService;
import com.myblog.utils.CodeGeneratorUtil;
import com.myblog.utils.JwtUtil;
import com.myblog.utils.MailUtils;
import com.myblog.vo.UserLoginVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;
import com.myblog.constant.JwtClaimsConstant;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

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
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private QrCodeService qrCodeService;



    @ApiOperation("界面登录")
    @PostMapping("login")
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
                .userName(currentUser.getUserName())
                .token(token)
                .imgUrl(currentUser.getImgUrl())
                .level(currentUser.getLevel())
                .userNickname(currentUser.getUserNickname())
                .build();
        return Result.success(userLoginVO);
    }
    @PostMapping(value = "/test")
    public Result<String> test1(){
        return Result.success();
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
    public Result<String> register(@RequestBody UserRegisterDTO currentUser) {
        if(
                currentUser.getUserName().isEmpty()
                || currentUser.getEmail().isEmpty()
                || currentUser.getPassword().isEmpty()
                || currentUser.getConfirmCode().isEmpty()
        ){
            return Result.error(MessageConstant.INFO_NOT_FULL);
        }
        // 检查用户是否已经存在
        String userName = currentUser.getUserName();
        String password = currentUser.getPassword();
        Boolean isUserExits = userService.isUserExits(userName);
        if(isUserExits){
            return Result.error(MessageConstant.USER_ALREADY_EXISTS);
        }
        // 查询验证码是否正确
        String confirmCode = currentUser.getConfirmCode();
        String codeInRedis = redisTemplate.opsForValue().get(currentUser.getEmail());

        if(Objects.equals(confirmCode, codeInRedis)){
            // 注册用户
            User user = User.builder()
                   .userName(userName)
                    .password(password)
                    .email(currentUser.getEmail())
                    .userNickname(userName)
                    .imgUrl("localhost:8080/img/avatar.png")
                    .birthday(null)
                    .mobilePhone(null)
                    .level(1)
                    .build();
            boolean isTrue = userService.register(user);
            if(isTrue){
                return Result.success(MessageConstant.REGISTER_SUCCESS);
            }else{
                return Result.error(MessageConstant.REGISTER_ERROR);
            }
        }
        return Result.error(MessageConstant.CODE_ERROR);
    }


    /**
     * 生成二维码
     */
    @GetMapping("generateQrCode")
    public Result generateQrCode() {
        return qrCodeService.generateQrCode();
    }


    /**
     * 检查二维码状态
     */
    @GetMapping("checkQrCode/{qrCodeId}")
    public Result checkQrCodeStatus(@PathVariable String qrCodeId) {
        return qrCodeService.checkQrCodeStatus(qrCodeId);
    }

    /**
     * 确认二维码登录
     */
    @PostMapping("confirmQrCodeLogin")
    public Result confirmQrCodeLogin(@RequestBody Map<String, Object> confirmForm) {
        String qrCodeId = (String) confirmForm.get("qrCodeId");
        Long userId = Long.valueOf(confirmForm.get("userId").toString());

        if (qrCodeId == null || userId == null) {
            return Result.error("参数不完整");
        }

        return qrCodeService.confirmQrCodeLogin(qrCodeId, userId);
    }

}