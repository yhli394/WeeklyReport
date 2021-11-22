package com.liyuehong.weeklyreport.mapper;

import com.liyuehong.weeklyreport.model.Article;

public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    /**
     * 增加文章
     * @param article
     * @return
     */
    int addNewArticle(Article article);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    /**
     * 更新
     * @param article
     * @return
     */
    int updateArticle(Article article);
}







