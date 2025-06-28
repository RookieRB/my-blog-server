package com.myblog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TagVO {
    // 标签id
    private Long tagId;
    // 标签名字
    private String tagName;
    // 标签别名
    private String aliasName;
    // 标签描述
    private String description;
}
