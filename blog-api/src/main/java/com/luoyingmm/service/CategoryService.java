package com.luoyingmm.service;

import com.luoyingmm.vo.CategoryVo;
import com.luoyingmm.vo.Result;

import java.util.List;

public interface CategoryService {
    CategoryVo findCategoryById(Long categoryId);

    Result findAll();

    Result findAllDetail();

    Result categoriesDetailById(Long id);
}
