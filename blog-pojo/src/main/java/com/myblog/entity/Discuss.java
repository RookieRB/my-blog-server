package com.myblog.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Discuss {

    // 评论ID
    private Long discussId;
    // 创建时间
    private LocalDateTime createTime;
    // 点赞数
    private Integer likeCount;
    // 发表用户
    private Long discussUser;
    // 评论文章ID
    private Long articleId;
    // 评论内容
    private String content;
    // 父评论Id
    private Long parentId;
}
