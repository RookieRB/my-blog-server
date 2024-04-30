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
}
