package com.myblog.mapper;


import com.github.pagehelper.Page;
import com.myblog.entity.Article;
import com.myblog.vo.ArticleVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;

@Mapper
public interface HomeMapper {
    @Select("select * from article")
    Page<ArticleVO> pageQuery();
    @Select("select * from article where article_user = #{id}")
    Page<ArticleVO> getArticleByUserId(Long id);
    @Select("select * from article as a left join article_tag_referenced as t on a.article_id = t.article_id where t.tag_id = 2 order by push_date desc")
    Page<ArticleVO> getRecommendMessage();
    @Select("select * from article as a left join article_tag_referenced as t on a.article_id = t.article_id where t.tag_id = 1 order by push_date desc")
    Page<ArticleVO> getHotMessage();
    @Select("select * from article as a left join article_category_referenced as t on a.article_id = t.article_id where t.category_id = 3 order by push_date desc")
    Page<ArticleVO> getAnimeMessage();
    @Select("select * from article as a left join article_category_referenced as t on a.article_id = t.article_id where t.category_id = 2 order by push_date desc")
    Page<ArticleVO> getFilmMessage();
    @Select("select * from article as a left join article_category_referenced as t on a.article_id = t.article_id where t.category_id = 1 order by push_date desc")
    Page<ArticleVO> getLifeMessage();
    @Select("select * from article as a left join article_category_referenced as t on a.article_id = t.article_id where t.category_id in (5,6,7) order by push_date desc ")
    Page<ArticleVO> getTechMessage();
    @Select("select * from article as a left join article_category_referenced as t on a.article_id = t.article_id where t.category_id = 5 order by push_date desc ")
    Page<ArticleVO> getOnlyTechMessage();
    @Select("select * from article as a left join article_category_referenced as t on a.article_id = t.article_id where t.category_id = 4 order by push_date desc ")
    Page<ArticleVO> getTourMessage();

}
