package com.myblog.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.myblog.dto.PageQueryDTO;
import com.myblog.entity.Article;
import com.myblog.mapper.HomeMapper;
import com.myblog.result.PageResult;
import com.myblog.service.HomeService;
import com.myblog.vo.ArticleVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class HomeServiceImpl implements HomeService {
    @Autowired
    private HomeMapper homeMapper;
    @Override
    public PageResult pageQuery(PageQueryDTO pageQueryDTO) {
        PageHelper.startPage(pageQueryDTO.getPage(),pageQueryDTO.getPageSize());
        Page<ArticleVO> page = homeMapper.pageQuery();

        return new PageResult(page.getTotal(),page.getResult());
    }
}
