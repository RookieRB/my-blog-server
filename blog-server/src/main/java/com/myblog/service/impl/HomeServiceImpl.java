package com.myblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myblog.dto.PageQueryDTO;
import com.myblog.entity.Article;
import com.myblog.entity.User;
import com.myblog.mapper.HomeMapper;
import com.myblog.mapper.UserMapper;
import com.myblog.result.PageResult;
import com.myblog.service.HomeService;
import com.myblog.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeMapper homeMapper;
    @Autowired
    private UserMapper userMapper;
    @Override
    public PageResult pageQuery(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.pageQuery();


        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getArticleByUserId(Long id, PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getArticleByUserId(id);
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getRecommendMessage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getRecommendMessage();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getHotMessage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getHotMessage();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getAnimeMessage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getAnimeMessage();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getFilmMessage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getFilmMessage();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getLifeMessage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getLifeMessage();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getTechMessage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getTechMessage();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getOnlyTechMessage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getOnlyTechMessage();
        return new PageResult(page.getTotal(),page.getResult());
    }

    @Override
    public PageResult getTourMessage(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.getTourMessage();
        return new PageResult(page.getTotal(),page.getResult());
    }



}
