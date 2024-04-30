package com.myblog.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    // 分类id
    private Long categoryId;
    // 分类名字
    private String categoryName;
    // 分类别名
    private String aliasName;
    // 分类描述
    private String description;
    // 父分类id
    private Long parentId;
    // 创建时间
    private LocalDateTime createTime;
}
