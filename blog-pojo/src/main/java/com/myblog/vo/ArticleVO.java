package com.myblog.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleVO {
    // 文章ID
    private Long articleId;
    // 发布时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime pushDate;
    // 发表用户
    private String articleUser;
    // 博文标题
    private String title;
    // 点赞数
    private Integer likeCount;
    // 评论数
    private Integer commentCount;
    // 浏览量
    private Integer readCount;
    // 文章摘要
    private String articleSummary;
    // 文章图片
    private String articleImg;
}
