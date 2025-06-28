package com.myblog.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ArticleDTO {
    // 发布时间
    private Long articleId;
    // @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime pushDate;
    // 发表用户id
    private Long articleUser;
    // 发表用户昵称
    private String articleUserNickname;
    // 博文标题
    private String title;
    // 是否置顶
    private Integer topFlag;
    // 创建时间
    //@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;
    // 文章摘要
    private String articleSummary;
    // 文章图片
    private String articleImg;
    // markdown格式
    private String contentMd;
    // html格式
    private String contentHtml;
    // 分类
    private Long category;
    // 标签
    private Long[] tags;
}
