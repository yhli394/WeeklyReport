package com.liyuehong.weeklyreport.mapper;

import com.liyuehong.weeklyreport.model.Article;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author yhli3
 */
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Article record);

    int insertSelective(Article record);

    Article selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Article record);

    int updateByPrimaryKeyWithBLOBs(Article record);

    int updateByPrimaryKey(Article record);
}