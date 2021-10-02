package com.luoyingmm.vo.params;

import com.luoyingmm.vo.CategoryVo;
import com.luoyingmm.vo.TagVo;
import lombok.Data;

import java.util.List;

@Data
public class ArticleParam {

    private Long id;

    private ArticleBodyParam body;

    private CategoryVo category;

    private String summary;

    private List<TagVo> tags;

    private String title;
}