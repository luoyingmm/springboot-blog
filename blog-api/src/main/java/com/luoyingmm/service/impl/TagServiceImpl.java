package com.luoyingmm.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.luoyingmm.dao.mapper.TagMapper;
import com.luoyingmm.dao.pojo.Tag;
import com.luoyingmm.service.TagService;
import com.luoyingmm.vo.Result;
import com.luoyingmm.vo.TagVo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagMapper tagMapper;
    @Override
    public List<TagVo> findTagByArticleId(Long articleId) {
        List<Tag> tags = tagMapper.findTagByArticleId(articleId);
        return copyList(tags);
    }

    @Override
    public Result hots(int limit) {
        List<Long> tagIds = tagMapper.findHotsTagIds(limit);
        if (CollectionUtils.isEmpty(tagIds)){
            return Result.success(Collections.emptyList());
        }
        List<Tag> tagList = tagMapper.findTagByTagIds(tagIds);
        return Result.success(tagList);
    }

    @Override
    public Result findAll() {
        List<Tag> tagList = tagMapper.selectList(new LambdaQueryWrapper<Tag>().select(Tag::getId,Tag::getTagName));

        return Result.success(copyList(tagList));
    }

    @Override
    public Result findAllDetail() {
        List<Tag> tagList = tagMapper.selectList(new LambdaQueryWrapper<Tag>());

        return Result.success(copyList(tagList));
    }

    public List<TagVo> copyList(List<Tag> tagList){
        List<TagVo> tagVos = new ArrayList<>();
        for (Tag tag : tagList) {
            tagVos.add(copy(tag));
        }
        return tagVos;
    }

    private TagVo copy(Tag tag) {
        TagVo tagVo = new TagVo();
        BeanUtils.copyProperties(tag,tagVo);
        return tagVo;
    }
}
