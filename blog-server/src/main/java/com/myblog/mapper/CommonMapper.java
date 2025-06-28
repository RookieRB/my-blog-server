package com.myblog.mapper;

import com.myblog.entity.Category;

import com.myblog.vo.TagVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface CommonMapper {
    @Select("select * from tag ")
    ArrayList<TagVO> getTags();
    @Select("select * from category")
    ArrayList<Category> getCategory();
}
