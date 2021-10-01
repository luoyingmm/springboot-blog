package com.luoyingmm.service;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.luoyingmm.dao.mapper.ArticleMapper;
import com.luoyingmm.dao.pojo.Article;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class ThreadService {
    @Async("taskExecutor")
    public void updateArticleViewCount(ArticleMapper articleMapper, Article article) {
        int viewCounts = article.getViewCounts();
        Article articleUpdate = new Article();
        articleUpdate.setViewCounts(viewCounts + 1);
        articleMapper.update(articleUpdate,new LambdaUpdateWrapper<Article>().eq(Article::getId,article.getId()).eq(Article::getViewCounts,article.getViewCounts()));
    }
}
