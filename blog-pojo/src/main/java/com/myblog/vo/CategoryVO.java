package com.myblog.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CategoryVO {
    // 分类id
    private Long categoryId;

    // 分类别名
    private String aliasName;

    // 孩子
    private List<CategoryVO> children;
}
