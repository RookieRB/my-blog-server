package com.myblog.entity;

import lombok.Data;

@Data
public class ArticleDetail {
    // 文章详细id
    private Long articleDetailId;
    // 文章markdown内容
    private String contentMd;
    // 文章html内容
    private String contentHtml;
    // 文章id
    private Long articleId;
}
