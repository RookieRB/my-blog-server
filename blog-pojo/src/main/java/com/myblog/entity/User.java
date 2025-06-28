package com.myblog.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="user")
public class User {
    // 用户id
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    // 用户名
    @Column(name="username",nullable = false)
    private String userName;
    // 用户密码
    @Column(name="password",nullable = false)
    private String password;
    // 用户昵称
    @Column(name="user_nickname",nullable = false)
    private String userNickname;
    // 用户邮箱
    @Column(name="email",nullable = false)
    private String email;
    // 用户创建时间
    @Column(name="create_time")
    private LocalDateTime createTime;
    // 用户生日
    @Column(name="birthday")
    private Date birthday;
    // 用户手机号
    @Column(name="mobile_phone")
    private String mobilePhone;
    // 用户头像
    @Column(name="img_url")
    private String imgUrl;
    // 用户等级
    @Column(name="level",nullable = false)
    private Integer level;
}
