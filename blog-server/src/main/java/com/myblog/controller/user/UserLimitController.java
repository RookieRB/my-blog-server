package com.myblog.controller.user;

import com.myblog.context.BaseContext;
import com.myblog.result.Result;
import com.myblog.service.UserService;
import com.myblog.vo.UserVO;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
@Api("C端受限制的用户相关接口")
public class UserLimitController {
    @Autowired
    UserService userService;

    @GetMapping("/getUserInfo")
    public Result<UserVO> getCurrentUserInfo(){
        Long currentUserId = BaseContext.getCurrentId();
        // 如果currentUserId为null，说明用户未登录或token无效
        if (currentUserId == null) {
            return Result.error("用户未登录或登录已过期");
        }

        UserVO userVO = userService.getCurrentUser(currentUserId);
        // 如果找不到用户信息，也返回错误
        if (userVO == null) {
            return Result.error("用户信息不存在");
        }
        return Result.success(userService.getCurrentUser(currentUserId));
    }
}
