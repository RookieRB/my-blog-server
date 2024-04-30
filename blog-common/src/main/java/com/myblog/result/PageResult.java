package com.myblog.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PageResult implements Serializable {
    // 总记录数
    private long total;

    // 当前页数集合
    private List data;
}
