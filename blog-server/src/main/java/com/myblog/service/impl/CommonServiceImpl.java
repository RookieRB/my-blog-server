package com.myblog.service.impl;

import com.myblog.entity.Category;
import com.myblog.mapper.CommonMapper;

import com.myblog.service.CommonService;

import com.myblog.utils.CategoryConverter;
import com.myblog.vo.CategoryVO;
import com.myblog.vo.TagVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommonServiceImpl implements CommonService {

    @Autowired
    private CommonMapper commonMapper;



    @Override
    public ArrayList<TagVO> getTags() {
        return commonMapper.getTags();
    }

    @Override
    public ArrayList<CategoryVO> getCategory() {
        ArrayList<Category> categories = commonMapper.getCategory();
        ArrayList<CategoryVO> categoryVOS = CategoryConverter.convertToCategoryVOList(categories);
        return categoryVOS;
    }

}
