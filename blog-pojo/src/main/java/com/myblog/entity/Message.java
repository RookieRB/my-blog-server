package com.myblog.entity;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Message {
    // 留言ID
    private Long messageId;
    // 创建时间
    private LocalDateTime createTime;
    // 点赞数
    private Integer likeCount;
    // 发表用户
    private Long messageUser;
    // 留言内容
    private String messageContent;
    // 父留言Id
    private Long parentId;
}
