package com.luoyingmm.service;

import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.params.CommentParam;

public interface CommentsService {
    /**
     * 根据文章ID查询评论
     * @param id
     * @return
     */
    Result commentsByArticleId(Long id);

    Result comment(CommentParam commentParam);
}
