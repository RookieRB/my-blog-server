package com.myblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myblog.constant.MessageConstant;
import com.myblog.dto.ArticleDTO;
import com.myblog.dto.PageQueryDTO;
import com.myblog.entity.Article;
import com.myblog.entity.ArticleCategoryReferenced;
import com.myblog.entity.ArticleDetail;
import com.myblog.entity.ArticleTagReferenced;
import com.myblog.exception.ArticleNotFoundException;
import com.myblog.mapper.ArticleMapper;
import com.myblog.result.PageResult;
import com.myblog.service.ArticleService;
import com.myblog.vo.ArticleManageVO;
import com.myblog.vo.ArticlePublishVO;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service
public class ArticleServiceImp implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;

    @Override
    @Transactional
    public ArticlePublishVO saveOrUpdateArticle(ArticleDTO articleDTO) {
        try {
            // 判断是新增还是更新
            if (articleDTO.getArticleId() != null) {
                // 更新文章
                return updateArticle(articleDTO);
            } else {
                // 新增文章
                return insertArticle(articleDTO);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("文章操作失败: " + e.getMessage());
        }
    }


    // 新增文章
    private ArticlePublishVO insertArticle(ArticleDTO articleDTO) {
        // 1. 保存文章基本信息
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        LocalDateTime now = LocalDateTime.now();
        // 设置初始值
        if (article.getPushDate() == null) {
            article.setPushDate(now);
        }
        if (article.getCreateTime() == null) {
            article.setCreateTime(now);
        }
        article.setLikeCount(0);
        article.setCommentCount(0);
        article.setReadCount(0);
        article.setTopFlag(0);

        // 插入文章并获取生成的ID
        articleMapper.insertArticle(article);
        Long articleId = article.getArticleId();

        // 2. 保存文章详情
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setContentMd(articleDTO.getContentMd());
        articleDetail.setContentHtml(articleDTO.getContentHtml());
        articleDetail.setArticleId(articleId);
        articleMapper.insertArticleDetail(articleDetail);

        // 3. 处理文章分类关联
        if (articleDTO.getCategory() != null) {
            Long categoryId = articleDTO.getCategory();
            ArticleCategoryReferenced acr = new ArticleCategoryReferenced();
            acr.setArticleId(articleId);
            acr.setCategoryId(categoryId);
            articleMapper.insertArticleCategory(acr);
        }

        // 4. 处理文章标签关联
        if (articleDTO.getTags() != null) {
            for (Long tagId : articleDTO.getTags()) {
                if (tagId != null) {
                    ArticleTagReferenced atr = new ArticleTagReferenced();
                    atr.setArticleId(articleId);
                    atr.setTagId(tagId);
                    articleMapper.insertArticleTag(atr);
                }
            }
        }

        ArticlePublishVO result = new ArticlePublishVO();
        result.setArticleId(articleId);
        result.setMessage("文章发布成功");
        return result;
    }

    // 更新文章
    private ArticlePublishVO updateArticle(ArticleDTO articleDTO) {
        Long articleId = articleDTO.getArticleId();

        // 1. 更新文章基本信息
        Article article = new Article();
        BeanUtils.copyProperties(articleDTO, article);
        articleMapper.updateArticle(article);

        // 2. 更新文章详情
        ArticleDetail articleDetail = new ArticleDetail();
        articleDetail.setContentMd(articleDTO.getContentMd());
        articleDetail.setContentHtml(articleDTO.getContentHtml());
        articleDetail.setArticleId(articleId);
        articleMapper.updateArticleDetail(articleDetail);

        // 3. 更新分类关联（先删除再添加）
        articleMapper.deleteArticleCategory(articleId);
        if (articleDTO.getCategory() != null) {
            Long categoryId = articleDTO.getCategory();
            ArticleCategoryReferenced acr = new ArticleCategoryReferenced();
            acr.setArticleId(articleId);
            acr.setCategoryId(categoryId);
            articleMapper.insertArticleCategory(acr);
        }

        // 4. 更新标签关联（差异化更新）
        // 4.1 获取当前文章的所有标签
        List<Long> currentTagIds = articleMapper.getArticleTagIds(articleId);
        List<Long> newTagIds = Arrays.asList(articleDTO.getTags());

        // 4.2 找出需要删除的标签（当前有但新提交没有的）
        List<Long> tagsToDelete = new ArrayList<>(currentTagIds);
        tagsToDelete.removeAll(newTagIds);
        if (!tagsToDelete.isEmpty()) {
            articleMapper.deleteArticleTagsByIds(articleId, tagsToDelete);
        }

        // 4.3 找出需要新增的标签（新提交有但当前没有的）
        List<Long> tagsToAdd = new ArrayList<>(newTagIds);
        tagsToAdd.removeAll(currentTagIds);
        for (Long tagId : tagsToAdd) {
            if (tagId != null) {
                ArticleTagReferenced atr = new ArticleTagReferenced();
                atr.setArticleId(articleId);
                atr.setTagId(tagId);
                articleMapper.insertArticleTag(atr);
            }
        }
        ArticlePublishVO result = new ArticlePublishVO();
        result.setArticleId(articleId);
        result.setMessage("文章更新成功");
        return result;
    }

    @Override
    public PageResult getArticleManageList(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(), pageQueryDTO.getPageSize());
        Page<ArticleManageVO> page = articleMapper.pageQuery();
        return new PageResult(page.getTotal(), page.getResult());
    }

    @Override
    public ArticleManageVO getArticleDetailById(Long articleId) {
        try {
            ArticleManageVO articleManageVO = articleMapper.getArticleDetailById(articleId);
            if(articleManageVO == null) {
                throw new ArticleNotFoundException(MessageConstant.ARTICLE_NOT_EXITS);
            }
            return articleManageVO;
        }catch(Exception e){
            e.printStackTrace();
            throw new ArticleNotFoundException(MessageConstant.ARTICLE_NOT_FOUND);
        }

    }
}
