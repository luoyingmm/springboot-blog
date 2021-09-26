package com.luoyingmm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luoyingmm.dao.dos.Archives;
import com.luoyingmm.dao.mapper.ArticleMapper;
import com.luoyingmm.dao.pojo.Article;
import com.luoyingmm.service.ArticleService;
import com.luoyingmm.service.SysUserService;
import com.luoyingmm.service.TagService;
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
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
    @Override
    public Result listArticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<Article>().orderByDesc(Article::getWeight, Article::getCreateDate);

        Page<Article> articlePage = articleMapper.selectPage(page, articleLambdaQueryWrapper);
        List<Article> records = articlePage.getRecords();

        List<ArticleVo> articleVoList = copyList(records,true,true);
        return Result.success(articleVoList);
    }

    @Override
    public Result hotArticle(int limit) {
        LambdaQueryWrapper<Article> last = new LambdaQueryWrapper<Article>().orderByDesc(Article::getViewCounts).select(Article::getId, Article::getTitle).last("limit " + limit);
        List<Article> articles = articleMapper.selectList(last);
        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result newArticles(int limit) {
        LambdaQueryWrapper<Article> last = new LambdaQueryWrapper<Article>().orderByDesc(Article::getCreateDate).select(Article::getId, Article::getTitle).last("limit " + limit);
        List<Article> articles = articleMapper.selectList(last);
        return Result.success(copyList(articles,false,false));
    }

    @Override
    public Result listArchives() {
        List<Archives> articles = articleMapper.listArchives();
        return Result.success(articles);
    }

    private List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor));
        }
        return articleVoList;
    }
    private ArticleVo copy(Article article,boolean isTag,boolean isAuthor){
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        if (isTag){
            articleVo.setTags(tagService.findTagByArticleId(article.getId()));
        }
        if (isAuthor){
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        return articleVo;
    }
}
