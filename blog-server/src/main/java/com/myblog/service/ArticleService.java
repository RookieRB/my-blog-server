package com.myblog.service;

import com.myblog.dto.ArticleDTO;
import com.myblog.dto.PageQueryDTO;
import com.myblog.result.PageResult;
import com.myblog.vo.ArticleManageVO;
import com.myblog.vo.ArticlePublishVO;

public interface ArticleService {
    ArticlePublishVO saveOrUpdateArticle(ArticleDTO articleDTO);

    PageResult getArticleManageList(PageQueryDTO pageQueryDTO);

    ArticleManageVO getArticleDetailById(Long articleId);
}
