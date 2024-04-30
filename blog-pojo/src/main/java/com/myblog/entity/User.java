package com.myblog.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.Date;

@Data
public class User {
    // 用户id
    private Long id;
    // 用户名
    private String userName;
    // 用户密码
    private String password;
    // 用户昵称
    private String userNickname;
    // 用户邮箱
    private String email;
    // 用户创建时间
    private LocalDateTime create_time;
    // 用户生日
    private Date birthday;
    // 用户手机号
    private String mobilePhone;
    // 用户头像
    private String imgUrl;
}
