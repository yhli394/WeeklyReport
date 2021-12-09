package com.liyuehong.weeklyreport.service;

import com.liyuehong.weeklyreport.mapper.ArticleMapper;
import com.liyuehong.weeklyreport.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhli3
 * @classname ArticleService
 * @Date 2021/11/22 20:35
 */
@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    public Article addNewArticle(Article article) {
            //如果文章id为空，那么是第一次提交
            if(article.getId()==null){
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                //设置发表日期
                article.setPublishDate(timestamp);
                int i = articleMapper.addNewArticle(article);
                return article;
            }else{
                Timestamp timestamp = new Timestamp(System.currentTimeMillis());
                article.setPublishDate(timestamp);
                int i = articleMapper.updateArticle(article);
                return article;
            }
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    public int deleteArticleById(Integer id) {
        int i = articleMapper.deleteArticleById(id);
        return i;
    }

    /**
     * 查询单个用户的所有文章
     * @param uid
     * @return
     */
    public List<Article> selectByUserId(Integer uid){
        List<Article> articles = articleMapper.selectByUserId(uid);
        return articles;
    }

    public List<Article> selectByWeek(String t1,String t2) {
        return articleMapper.selectByWeek(t1,t2);
    }
}



