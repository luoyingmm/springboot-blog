package com.luoyingmm.service;

import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.TagVo;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface TagService {
    List<TagVo> findTagByArticleId(Long articleId);


    Result hots(int limit);
}
