package com.myblog.service.impl;

import com.myblog.constant.MessageConstant;
import com.myblog.dto.UserLoginDTO;
import com.myblog.entity.User;
import com.myblog.exception.AccountNotFoundException;
import com.myblog.exception.PasswordErrorException;
import com.myblog.mapper.UserMapper;
import com.myblog.service.UserService;
import com.myblog.vo.UserLoginVO;
import com.myblog.vo.UserVO;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

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

    @Override
    public boolean isUserExits(String username) {
        User user = userMapper.getUserByName(username);
        if(user == null){
            return false;
        }
        return true;
    }

    @Override
    public boolean register(User user) {
        user.setCreateTime(LocalDateTime.now());
        int result = userMapper.insertUser(user);
        if(result == 1) {
            return true;     // 注册成功
        }
        return false;    // 注册失败
    }

    @Override
    public UserVO getCurrentUser(Long currentUserId) {
        User currentUser = userMapper.getuserBuyId(currentUserId);
        if (currentUser == null) {
            return null; // 或者抛异常，视情况而定
        }

        UserVO userVO = new UserVO();
        BeanUtils.copyProperties(currentUser,userVO);
        return userVO;
    }
}
