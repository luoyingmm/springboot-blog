package com.luoyingmm.service;

import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.PageParams;

public interface ArticleService {
    /**
     * 首页文章显示
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);
}
