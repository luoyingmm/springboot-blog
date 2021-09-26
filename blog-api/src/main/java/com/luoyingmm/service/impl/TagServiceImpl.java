package com.luoyingmm.service.impl;

import com.luoyingmm.dao.mapper.TagMapper;
import com.luoyingmm.dao.pojo.Tag;
import com.luoyingmm.service.TagService;
import com.luoyingmm.vo.TagVo;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
