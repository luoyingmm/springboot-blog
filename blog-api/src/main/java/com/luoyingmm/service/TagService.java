package com.luoyingmm.service;

import com.luoyingmm.vo.TagVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagService {
    List<TagVo> findTagByArticleId(Long articleId);
}
