package com.liyuehong.weeklyreport.mapper;

import com.liyuehong.weeklyreport.model.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import javax.websocket.server.PathParam;
import java.sql.Date;
import java.util.List;


//@Component
@Repository
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

    /**
     * 按周查询周报
     * @param t1
     * @param t2
     * @return
     */
    List<Article> selectByWeek(@Param("t") String t1, @Param("u") String t2);

    Article showArticle(Integer id);

    List<Article> selectAllArticle();

    List<Integer> selectAllUser();

}