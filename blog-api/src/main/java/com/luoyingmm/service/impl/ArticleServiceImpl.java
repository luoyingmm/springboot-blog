package com.luoyingmm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luoyingmm.dao.dos.Archives;
import com.luoyingmm.dao.mapper.ArticleBodyMapper;
import com.luoyingmm.dao.mapper.ArticleMapper;
import com.luoyingmm.dao.mapper.ArticleTagMapper;
import com.luoyingmm.dao.pojo.Article;
import com.luoyingmm.dao.pojo.ArticleBody;
import com.luoyingmm.dao.pojo.ArticleTag;
import com.luoyingmm.dao.pojo.SysUser;
import com.luoyingmm.service.*;
import com.luoyingmm.utils.UserThreadLocal;
import com.luoyingmm.vo.ArticleBodyVo;
import com.luoyingmm.vo.ArticleVo;
import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.TagVo;
import com.luoyingmm.vo.params.ArticleParam;
import com.luoyingmm.vo.params.PageParams;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private ArticleTagMapper articleTagMapper;
//    @Override
//    public Result listArticle(PageParams pageParams) {
//        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
//        LambdaQueryWrapper<Article> articleLambdaQueryWrapper = new LambdaQueryWrapper<Article>();
//        if (pageParams.getCategoryId() != null){
//            articleLambdaQueryWrapper.eq(Article::getCategoryId,pageParams.getCategoryId());
//        }
//        ArrayList<Long> articleIdList = new ArrayList<>();
//        if (pageParams.getTagId() != null){
//            LambdaQueryWrapper<ArticleTag> articleTagLambdaQueryWrapper = new LambdaQueryWrapper<>();
//            articleTagLambdaQueryWrapper.eq(ArticleTag::getTagId,pageParams.getTagId());
//            List<ArticleTag> articleTags = articleTagMapper.selectList(articleTagLambdaQueryWrapper);
//            for (ArticleTag articleTag : articleTags) {
//                articleIdList.add(articleTag.getArticleId());
//            }
//            if (articleIdList.size() > 0){
//                articleLambdaQueryWrapper.in(Article::getId,articleIdList);
//            }
//
//        }
//
//        articleLambdaQueryWrapper.orderByDesc(Article::getWeight, Article::getCreateDate);
//
//        Page<Article> articlePage = articleMapper.selectPage(page, articleLambdaQueryWrapper);
//        List<Article> records = articlePage.getRecords();
//
//        List<ArticleVo> articleVoList = copyList(records,true,true);
//        return Result.success(articleVoList);
//    }

    @Override
    public Result listArticle(PageParams pageParams) {
        Page<Article> page = new Page<>(pageParams.getPage(), pageParams.getPageSize());
        IPage<Article> articleIPage = articleMapper.listArticle(page, pageParams.getCategoryId(), pageParams.getTagId(), pageParams.getYear(), pageParams.getMonth());
        List<Article> records = articleIPage.getRecords();
        System.out.println("===========================" + records);
        return Result.success(copyList(records,true,true));
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

    @Autowired
    private ThreadService threadService;

    @Override
    public Result findArticleById(Long articleId) {
        Article article = articleMapper.selectById(articleId);
        ArticleVo articleVo = copy(article, true, true,true,true);
        threadService.updateArticleViewCount(articleMapper,article);
        return Result.success(articleVo);
    }

    @Override
    public Result publish(ArticleParam articleParam) {
        SysUser sysUser = UserThreadLocal.get();

        Article article = new Article();
        article.setAuthorId(sysUser.getId());
        article.setWeight(Article.Article_Common);
        article.setViewCounts(0);
        article.setTitle(articleParam.getTitle());
        article.setSummary(articleParam.getSummary());
        article.setCommentCounts(0);
        article.setCreateDate(System.currentTimeMillis());
        article.setCategoryId(Long.parseLong(articleParam.getCategory().getId()));
        this.articleMapper.insert(article);
        List<TagVo> tags = articleParam.getTags();
        if (tags != null){
            for (TagVo tag : tags) {
                Long articleId = article.getId();
                ArticleTag articleTag = new ArticleTag();
                articleTag.setTagId(Long.parseLong(tag.getId()));
                articleTag.setArticleId(articleId);
                articleTagMapper.insert(articleTag);
            }
        }

        ArticleBody articleBody = new ArticleBody();
        articleBody.setArticleId(article.getId());
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        articleBodyMapper.insert(articleBody);
        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);
        HashMap<String, String> map = new HashMap<>();
        map.put("id",article.getId().toString());
        return Result.success(map);
    }

    private List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,false,false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records,boolean isTag,boolean isAuthor,boolean isBody,boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record,isTag,isAuthor,isBody,isCategory));
        }
        return articleVoList;
    }
    private ArticleVo copy(Article article,boolean isTag,boolean isAuthor,boolean isBody,boolean isCategory){
        ArticleVo articleVo = new ArticleVo();
        articleVo.setId(String.valueOf(article.getId()));
        BeanUtils.copyProperties(article,articleVo);
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        if (isTag){
            articleVo.setTags(tagService.findTagByArticleId(article.getId()));
        }
        if (isAuthor){
            Long authorId = article.getAuthorId();
            articleVo.setAuthor(sysUserService.findUserById(authorId).getNickname());
        }
        if (isBody){
            articleVo.setBody(findArticleBodyById(article.getBodyId()));
        }
        if (isCategory){
            Long categoryId = article.getCategoryId();
            articleVo.setCategoryVo(categoryService.findCategoryById(categoryId));
        }
        return articleVo;
    }

    @Autowired
    private ArticleBodyMapper articleBodyMapper;
    private ArticleBodyVo findArticleBodyById(Long bodyId) {
        ArticleBody article = articleBodyMapper.selectById(bodyId);
        ArticleBodyVo articleBodyVo = new ArticleBodyVo();
        articleBodyVo.setContent(article.getContent());
        return articleBodyVo;
    }
}
