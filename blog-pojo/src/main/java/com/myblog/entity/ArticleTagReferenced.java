package com.myblog.entity;

import lombok.Data;

@Data
public class ArticleTagReferenced {
    // 引用id
    private Long atrId;
    // 文章Id
    private Long articleId;
    // 标签id
    private Long tagId;
}
