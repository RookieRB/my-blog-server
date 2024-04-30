package com.myblog.service.impl;

import com.myblog.constant.MessageConstant;
import com.myblog.dto.UserLoginDTO;
import com.myblog.entity.User;
import com.myblog.exception.AccountNotFoundException;
import com.myblog.exception.PasswordErrorException;
import com.myblog.mapper.UserMapper;
import com.myblog.service.UserService;
import com.myblog.vo.UserLoginVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Override
    public User login(UserLoginDTO userinfo) {
        String userName = userinfo.getUserName();
        String password = userinfo.getPassword();
        // 根据用户名去数据库中查询用户信息
        User currentUser = userMapper.getUserByName(userName);
        // 判断账户是否存在
        if(currentUser == null){
            throw new AccountNotFoundException(MessageConstant.ACCOUNT_NOT_FOUND);
        }
        // 判断账户密码是否正确
        if(!password.equals(currentUser.getPassword())){
            throw new PasswordErrorException(MessageConstant.PASSWORD_ERROR);
        }
        //返回对象
        return currentUser;
    }
}
