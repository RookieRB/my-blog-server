package com.myblog.controller.home;

import com.myblog.dto.PageQueryDTO;
import com.myblog.entity.Article;
import com.myblog.result.PageResult;
import com.myblog.result.Result;
import com.myblog.service.HomeService;
import com.myblog.vo.ArticleVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
@Api(tags = "首页相关接口")
@Slf4j
public class HomeController {
    @Autowired
    private HomeService homeService;

    @GetMapping("/page")
    @ApiOperation("获取文章数据")
    public Result<PageResult> page(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.pageQuery(pageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/getArticleByUserId/{id}")
    @ApiOperation("根据用户id获取文章数据")
    public Result<PageResult> getArticleByUserId(@PathVariable("id") Long id,PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getArticleByUserId(id,pageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/getRecommendMessage")
    @ApiOperation("获取推荐文章数据")
    public Result<PageResult> getRecommendMessage(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getRecommendMessage(pageQueryDTO);
        return Result.success(pageResult);
    }
    @GetMapping("/getHotMessage")
    @ApiOperation("获取热门文章数据")
    public Result<PageResult> getHotMessage(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getHotMessage(pageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/getAnimeMessage")
    @ApiOperation("获取动漫文章数据")
    public Result<PageResult> getAnimeMessage(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getAnimeMessage(pageQueryDTO);
        return Result.success(pageResult);
    }
    @GetMapping("/getFilmMessage")
    @ApiOperation("获取影视文章数据")
    public Result<PageResult> getFilmMessage(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getFilmMessage(pageQueryDTO);
        return Result.success(pageResult);
    }

    @GetMapping("/getLifeMessage")
    @ApiOperation("获取生活文章数据")
    public Result<PageResult> getLifeMessage(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getLifeMessage(pageQueryDTO);
        return Result.success(pageResult);
    }
    @GetMapping("/getTourMessage")
    @ApiOperation("获取旅游文章数据")
    public Result<PageResult> getTourMessage(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getTourMessage(pageQueryDTO);
        return Result.success(pageResult);
    }
    @GetMapping("/getTechAllMessage")
    @ApiOperation("获取科技所有文章数据")
    public Result<PageResult> getTechAllMessage(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getTechMessage(pageQueryDTO);
        return Result.success(pageResult);
    }
    @GetMapping("/getTechMessage")
    @ApiOperation("获取科技文章数据")
    public Result<PageResult> getOnlyTechMessage(PageQueryDTO pageQueryDTO){
        PageResult pageResult = homeService.getOnlyTechMessage(pageQueryDTO);
        return Result.success(pageResult);
    }
}
