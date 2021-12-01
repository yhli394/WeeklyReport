package com.liyuehong.weeklyreport.mapper;

import com.liyuehong.weeklyreport.model.Article;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ArticleMapper {
    int insert(Article record);

    int insertSelective(Article record);

    List<Article> selectByUserId(Integer uid);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);

    int addNewArticle(Article article);

    int updateArticle(Article article);

    int deleteArticleById(Integer id);

}