package com.myblog.service;

import com.myblog.result.Result;
import com.myblog.vo.CategoryVO;
import com.myblog.vo.TagVO;

import java.util.ArrayList;

public interface CommonService {
    ArrayList<TagVO> getTags();

    ArrayList<CategoryVO> getCategory();
}
