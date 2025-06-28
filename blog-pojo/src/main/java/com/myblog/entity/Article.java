package com.myblog.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Article {
    // 文章ID
    private Long articleId;
    // 发布时间
   // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pushDate;
    // 发表用户id
    private Long articleUser;
    // 发表用户昵称
    private String articleUserNickname;
    // 文章内容
    // 博文标题
    private String title;
    // 点赞数
    private Integer likeCount;
    // 评论数
    private Integer commentCount;
    // 浏览量
    private Integer readCount;
    // 是否置顶
    private Integer topFlag;
    // 创建时间
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    // 文章摘要
    private String articleSummary;
    // 文章图片
    private String articleImg;
}
