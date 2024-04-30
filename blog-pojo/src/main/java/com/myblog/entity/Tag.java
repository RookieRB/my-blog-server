package com.myblog.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Tag {
    // 标签id
    private Long tagId;
    // 标签名字
    private String tagName;
    // 标签别名
    private String aliasName;
    // 标签描述
    private String description;
    // 创建时间
    private LocalDateTime createTime;
}
