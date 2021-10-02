package com.luoyingmm.service;

import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.ArticleParam;
import com.luoyingmm.vo.params.PageParams;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;


public interface ArticleService {
    /**
     * 首页文章显示
     * @param pageParams
     * @return
     */
    Result listArticle(PageParams pageParams);

    /**
     * 最热文章
     * @param limit
     * @return
     */
    Result hotArticle(int limit);

    /**
     * 最新文章
     * @param limit
     * @return
     */
    Result newArticles(int limit);

    /**
     * 文章归档
     * @return
     */
    Result listArchives();

    Result findArticleById(Long articleId);

    Result publish(ArticleParam articleParam);
}
