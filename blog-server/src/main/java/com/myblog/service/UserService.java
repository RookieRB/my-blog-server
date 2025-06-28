package com.myblog.service;

import com.myblog.dto.UserLoginDTO;
import com.myblog.entity.User;
import com.myblog.vo.UserLoginVO;
import com.myblog.vo.UserVO;

public interface UserService {
    User login(UserLoginDTO userinfo);

    boolean isUserExits(String username);
    boolean register(User user);

    UserVO getCurrentUser(Long currentUserId);
}
