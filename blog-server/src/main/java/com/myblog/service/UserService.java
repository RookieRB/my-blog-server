package com.myblog.service;

import com.myblog.dto.UserLoginDTO;
import com.myblog.entity.User;
import com.myblog.vo.UserLoginVO;

public interface UserService {
    User login(UserLoginDTO userinfo);
}
