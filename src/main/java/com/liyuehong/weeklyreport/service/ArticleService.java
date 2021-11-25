package com.liyuehong.weeklyreport.service;

import com.liyuehong.weeklyreport.mapper.ArticleMapper;
import com.liyuehong.weeklyreport.model.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

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

}



