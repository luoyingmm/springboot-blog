package com.luoyingmm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luoyingmm.dao.mapper.ArticleMapper;
import com.luoyingmm.dao.pojo.Article;
import com.luoyingmm.service.ArticleService;
import com.luoyingmm.vo.ArticleVo;
import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Override
    public Result listArticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<Article>().orderByDesc(Article::getWeight, Article::getCreateDate);

        Page<Article> articlePage = articleMapper.selectPage(page, articleLambdaQueryWrapper);
        List<Article> records = articlePage.getRecords();

        List<ArticleVo> articleVoList = copyList(records);
        return Result.success(articleVoList);
    }

    private List<ArticleVo> copyList(List<Article> records) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record));
        }
        return articleVoList;
    }
    private ArticleVo copy(Article article){
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        return articleVo;
    }
}
