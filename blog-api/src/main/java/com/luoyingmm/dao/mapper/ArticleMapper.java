package com.luoyingmm.dao.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.luoyingmm.dao.dos.Archives;
import com.luoyingmm.dao.pojo.Article;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleMapper extends BaseMapper<Article> {

    List<Archives> listArchives();

}
