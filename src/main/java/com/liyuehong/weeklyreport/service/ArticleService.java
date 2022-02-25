package com.liyuehong.weeklyreport.service;

import com.google.common.collect.ImmutableMap;
import com.liyuehong.weeklyreport.configuration.CustomException;
import com.liyuehong.weeklyreport.mapper.ArticleMapper;
import com.liyuehong.weeklyreport.model.Article;
import com.liyuehong.weeklyreport.utils.ErrorInfoEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author yhli3
 * @classname ArticleService
 * @Date 2021/11/22 20:35
 */
@Service
@CacheConfig(cacheNames = "article")
public class ArticleService {

    private final static Logger logger = LoggerFactory.getLogger(ArticleService.class);

    @Autowired
    ArticleMapper articleMapper;

    @Autowired
    RedisTemplate redisTemplate;

    /**
     * 更新文章
     * @param article
     * @return
     */
    @CachePut(key = "#result.id")
    public Article updateArticle(Article article) {
        article.setPublishDate(new Timestamp(System.currentTimeMillis()));
        if(articleMapper.updateArticle(article)!=1){
            throw new CustomException(ErrorInfoEnum.UPDATE_FAILED,ImmutableMap.of("article:",article));
        }
        return articleMapper.showArticle(article.getId());
    }

    /**
     * 创建文章
     * @param article
     * @return
     */
//    @CachePut(key = "#article.id")
//    @Caching
    // TODO: 2022/2/20 咨询蒋师兄增，删，改返回受影响的行数好还是返回实体类的信息好？
    public int addNewArticle(Article article) {
        //如果文章id为空，那么是第一次提交
        if(article.getId()!=null){
            throw new CustomException(ErrorInfoEnum.CREATE_ARTICLE_FAILED,ImmutableMap.of("id",article.getId()));
        }
        //判断文章内容是否为空
        if(article.getContent().isEmpty()){
            throw new CustomException(ErrorInfoEnum.NULL_POINTER_EXCEPTION, ImmutableMap.of("content:",article.getContent()));
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        //设置发表日期
        article.setPublishDate(timestamp);
        return articleMapper.addNewArticle(article);
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
//    @CacheEvict(key = "#uid")
    public List<Article> selectByUserId(Integer uid){
        List<Article> articles = articleMapper.selectByUserId(uid);
        return articles;
    }

    /**
     * 查询本周周报
     * @param t1
     * @param t2
     * @return
     */
    public List<Article> selectByWeek(String t1,String t2) {
        return articleMapper.selectByWeek(t1,t2);
    }

    /**
     * 根据文章id渲染文章内容
     * @param id
     * @return
     */
    @Cacheable(key ="#id")
    public Article showArticle(Integer id) {
        Article article = articleMapper.showArticle(id);
//        redisTemplate.opsForHash().put(id+"",id+"",article);
//        logger.debug(redisTemplate.opsForHash().get(id+"",id+"").toString());
        return article;
    }

    /**
     * 查询所有文章
     * @return
     */
    // TODO: 2022/2/20 如何解决缓存和数据库同步更新的问题？
//    @Cacheable(key = "#result")
    public List<Article> selectAllArticle(){
        return articleMapper.selectAllArticle();
    }

}



