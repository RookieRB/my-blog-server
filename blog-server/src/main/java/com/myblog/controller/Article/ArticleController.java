package com.myblog.controller.Article;


import com.myblog.dto.ArticleDTO;
import com.myblog.dto.PageQueryDTO;
import com.myblog.entity.Article;
import com.myblog.entity.Category;
import com.myblog.result.PageResult;
import com.myblog.result.Result;
import com.myblog.service.ArticleService;
import com.myblog.vo.ArticleManageVO;
import com.myblog.vo.ArticlePublishVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;


    @PostMapping("/publish")
    public Result<ArticlePublishVO> publishArticle(@RequestBody ArticleDTO articleDTO) {
        return Result.success(articleService.saveOrUpdateArticle(articleDTO));
    }


    @GetMapping("/getArticleManageList")
    public Result<PageResult> getArticleManageList(PageQueryDTO pageQueryDTO) {

        return Result.success(articleService.getArticleManageList(pageQueryDTO));
    }

    @GetMapping("/getArticleById/{articleId}")
    public Result<ArticleManageVO> getArticleDetailById(@PathVariable("articleId") Long articleId) {
        return Result.success(articleService.getArticleDetailById(articleId));
    }
}
