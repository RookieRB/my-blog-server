package com.myblog.controller.common;

import com.myblog.result.Result;
import com.myblog.service.CommonService;
import com.myblog.vo.CategoryVO;
import com.myblog.vo.TagVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
@RequestMapping("/common")
@Api(tags = "通用类接口")
public class CommonController {
    @Autowired
    private CommonService commonService;
    @GetMapping("/getTags")
    @ApiOperation("获取标签列表")
    public Result<ArrayList<TagVO>> getTags() {
        return Result.success(commonService.getTags());
    }
    @GetMapping("/getCategory")
    @ApiOperation("获取分类列表")
    public Result<ArrayList<CategoryVO>> getCategory() {
        return Result.success(commonService.getCategory());
    }
}
