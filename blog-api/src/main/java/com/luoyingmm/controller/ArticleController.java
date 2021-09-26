package com.luoyingmm.controller;

import com.luoyingmm.service.ArticleService;
import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.PageParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.ResultSet;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    ArticleService articleService;
    /**
     * 首页文章列表
     * @param pageParams
     * @return
     */
    @PostMapping
    public Result listArticle(@RequestBody PageParams pageParams){
         return articleService.listArticle(pageParams);
    }

    /**
     * 首页最热文章
     */

    @PostMapping("hot")
    public Result hotArticle(){
        int limit = 5;
        return articleService.hotArticle(limit);
    }

    /**
     * 首页最新文章
     */

    @PostMapping("new")
    public Result newArticles(){
        int limit = 5;
        return articleService.newArticles(limit);
    }

    /**
     * 首页最新文章
     */

    @PostMapping("listArchives")
    public Result listArchives(){
        return articleService.listArchives();
    }
}
