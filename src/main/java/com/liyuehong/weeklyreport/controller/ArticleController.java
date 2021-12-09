package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.Article;
import com.liyuehong.weeklyreport.service.ArticleService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.List;

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

    @ApiOperation("删除文章接口")
    @RequestMapping(value = "/delete/{aid}",method = RequestMethod.DELETE)
    public RespMsg deleteArticleById(@PathVariable Integer id){
        int i = articleService.deleteArticleById(id);
        if(i==1){
            return new RespMsg("删除成功");
        }
        return new RespMsg("删除失败");
    }

    @ApiOperation("查询本周周报接口")
    @RequestMapping(value = "/selectAllReports",method = RequestMethod.GET)
    public List<Article> selectByWeek(Date time){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(time);
        int i = cal.get(Calendar.DAY_OF_WEEK);
        if(i==1){
            cal.add(Calendar.DAY_OF_WEEK,-1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        int day = cal.get(Calendar.DAY_OF_WEEK);
        cal.add(Calendar.DATE,cal.getFirstDayOfWeek()-day);
        //周一
        String monday = sdf.format(cal.getTime());
        cal.add(Calendar.DATE,6);
        //周日
        String sunday = sdf.format(cal.getTime());
        return articleService.selectByWeek(monday,sunday);
    }

    @ApiOperation("查询单个用户所有文章接口")
    @RequestMapping(value = "/select/{uid}",method = RequestMethod.GET)
    public List<Article> selectByUserId(@PathVariable Integer uid){
        List<Article> articles = articleService.selectByUserId(uid);
        return articles;
    }

}







