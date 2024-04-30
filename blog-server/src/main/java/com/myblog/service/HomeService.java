package com.myblog.service;

import com.myblog.dto.PageQueryDTO;
import com.myblog.result.PageResult;


public interface HomeService {

    PageResult pageQuery(PageQueryDTO pageQueryDTO);
}
