package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.Article;
import com.liyuehong.weeklyreport.service.ArticleService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yhli3
 * @classname ArticleController
 * @Date 2021/11/21 15:23
 */
@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/",method = RequestMethod.POST)
    public RespMsg addNewArticle(Article article) {
        int result = articleService.addNewArticle(article);
        if (result == 1) {
            return new RespMsg(200, article.getId() + "");
        } else {
            return new RespMsg(500,"文章发表或保存失败");
        }
    }
}
