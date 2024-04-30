package com.myblog.entity;

import lombok.Data;

@Data
public class ArticleCategoryReferenced {
    // 引用id
    private Long acrId;
    // 文章id
    private Long articleId;
    // 类目id
    private Long categoryId;
}
