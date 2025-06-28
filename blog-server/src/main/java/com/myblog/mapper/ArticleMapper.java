package com.myblog.mapper;

import com.github.pagehelper.Page;
import com.myblog.entity.Article;
import com.myblog.entity.ArticleCategoryReferenced;
import com.myblog.entity.ArticleDetail;
import com.myblog.entity.ArticleTagReferenced;
import com.myblog.vo.ArticleManageVO;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface ArticleMapper {

    // 更新文章
    int updateArticle(Article article);

    // 更新文章详情
    int updateArticleDetail(ArticleDetail articleDetail);



    // 插入文章并返回自动生成的主键
    @Options(useGeneratedKeys = true, keyProperty = "articleId")
    int insertArticle(Article article);

    // 插入文章详情
    int insertArticleDetail(ArticleDetail articleDetail);

    // 插入文章分类关联
    int insertArticleCategory(ArticleCategoryReferenced articleCategoryReferenced);

    // 插入文章标签关联
    int insertArticleTag(ArticleTagReferenced articleTagReferenced);




    @Delete("DELETE FROM article_category_referenced WHERE article_id = #{articleId}")
    int deleteArticleCategory(Long articleId);

    // 删除文章标签关联
    @Delete("DELETE FROM article_tag_referenced WHERE article_id = #{articleId}")
    int deleteArticleTags(Long articleId);

    // 根据标签ID列表删除特定的文章-标签关联
    void deleteArticleTagsByIds(@Param("articleId") Long articleId, @Param("tagIds") List<Long> tagIds);





    // 获取文章的所有标签ID
    @Select("SELECT tag_id FROM article_tag_referenced WHERE article_id = #{articleId}")
    List<Long> getArticleTagIds(Long articleId);

    // 根据分类名称查询分类ID
    @Select("select category_id from category where alias_name = #{categoryName}")
    Long getCategoryIdByName(String categoryName);

    // 根据标签名称查询标签ID
    @Select("select tag_id from tag where tag_name = #{tagName}")
    Long getTagIdByName(String tagName);

    // 根据ID获取文章
    @Select("SELECT * FROM article WHERE article_id = #{articleId}")
    Article getArticleById(Long articleId);

    // 获取文章全部信息
    Page<ArticleManageVO> pageQuery();

    // 获取单个文章信息
    ArticleManageVO getArticleDetailById(Long articleId);

}
