package com.luoyingmm.service;

import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.PageParams;
import org.springframework.context.annotation.ComponentScan;

public interface ArticleService {
    /**
     * 首页文章显示
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);
}
