package com.luoyingmm.dao.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luoyingmm.dao.pojo.Tag;
import com.luoyingmm.vo.TagVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagMapper extends BaseMapper<Tag> {
    /**
     * 根据文章ID查询标签列表
     * @param articleId
     */
    List<Tag> findTagByArticleId(Long articleId);


    List<Long> findHotsTagIds(int limit);

    List<Tag> findTagByTagIds(List<Long> tagIds);
}
