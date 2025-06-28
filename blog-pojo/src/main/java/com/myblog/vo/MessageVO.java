package com.myblog.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MessageVO {
    // 留言ID
    private Long messageId;
    // 创建时间
    private LocalDateTime createTime;
    // 点赞数
    private Integer likeCount;
    // 发表用户昵称
    private String userNickname;
    // 发表用户头像
    private String imgUrl;
    // 发表用户等级
    private Integer level;
    // 留言内容
    private String messageContent;
    // 子留言
    private ArrayList<MessageVO> childMessages;
}
