package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.Article;
import com.liyuehong.weeklyreport.service.ArticleService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    @ApiOperation("提交文章接口")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public Article addNewArticle(@RequestBody Article article) {
        Article newArticle = articleService.addNewArticle(article);
        return newArticle;
    }


}






