package com.myblog.mapper;

import com.github.pagehelper.Page;
import com.myblog.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface HomeMapper {
    @Select("select * from article")
    Page<ArticleVO> pageQuery();
}
