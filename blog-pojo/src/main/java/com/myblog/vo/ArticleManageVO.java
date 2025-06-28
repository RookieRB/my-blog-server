package com.myblog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Arrays;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ArticleManageVO {
    // 文章ID
    private Long articleId;
    // 发布时间
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime pushDate;
    // 发表用户id
    private Long articleUser;
    // 发表用户昵称
    private String articleUserNickname;
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

    // 分类
    private Long categoryId;
    private String categoryName;

    private String contentMd;
    private String contentHtml;



    // 标签
    private Long[] tagIds;
    private String[] tagNames;

    // 自定义 setter 处理 GROUP_CONCAT 结果
    public void setTagIds(String tagIdStr) {
        if (tagIdStr != null && !tagIdStr.isEmpty()) {
            this.tagIds = Arrays.stream(tagIdStr.split(","))
                    .map(Long::valueOf)
                    .toArray(Long[]::new);
        } else {
            this.tagIds = new Long[0];
        }
    }

    public void setTagNames(String tagNameStr) {
        if (tagNameStr != null && !tagNameStr.isEmpty()) {
            this.tagNames = tagNameStr.split(",");
        } else {
            this.tagNames = new String[0];
        }
    }

}
