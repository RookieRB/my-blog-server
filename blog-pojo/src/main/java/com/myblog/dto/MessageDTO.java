package com.myblog.dto;

import com.myblog.vo.MessageVO;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
public class MessageDTO {
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
    // 父留言
    private Long parentId;
}
