package com.myblog.service;

import com.myblog.dto.PageQueryDTO;
import com.myblog.result.PageResult;


public interface HomeService {

    PageResult pageQuery(PageQueryDTO pageQueryDTO);

    PageResult getArticleByUserId(Long id, PageQueryDTO pageQueryDTO);

    PageResult getRecommendMessage(PageQueryDTO pageQueryDTO);

    PageResult getHotMessage(PageQueryDTO pageQueryDTO);

    PageResult getAnimeMessage(PageQueryDTO pageQueryDTO);

    PageResult getFilmMessage(PageQueryDTO pageQueryDTO);

    PageResult getLifeMessage(PageQueryDTO pageQueryDTO);

    PageResult getTechMessage(PageQueryDTO pageQueryDTO);

    PageResult getOnlyTechMessage(PageQueryDTO pageQueryDTO);

    PageResult getTourMessage(PageQueryDTO pageQueryDTO);
}
