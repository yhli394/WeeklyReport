package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.Article;
import com.liyuehong.weeklyreport.service.ArticleService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
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

    @ApiOperation("提交或更新文章接口(返回消息中的msg右边的数字是文章的id)")
    @RequestMapping(value = "/add",method = RequestMethod.POST)
    public RespMsg addNewArticle(@RequestBody Article article) {
        if(article.getContent().isEmpty()){
            return new RespMsg("error","文章内容不能为空！");
        }
        if(articleService.addNewArticle(article)==1){
            return new RespMsg("success",article.getId()+"");
        }
        return new RespMsg("error","文章提交失败，请联系管理员！");
    }

    @ApiImplicitParam(name = "id",value = "文章的id",required = true)
    @ApiOperation("根据文章的id删除文章")
    @RequestMapping(value = {"/delete/{id}"},method = {RequestMethod.DELETE})
    public RespMsg deleteArticleById(@PathVariable Integer id){
        int i = articleService.deleteArticleById(id);
        if(i==1){
            return new RespMsg("删除成功");
        }
        return new RespMsg("删除失败");
    }

    @ApiImplicitParam(name = "time",value = "时间",required = true)
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

    @ApiOperation("根据用户id查询单个用户所有文章接口")
    @RequestMapping(value = "/select/{uid}",method = RequestMethod.GET)
    public List<Article> selectByUserId(@PathVariable Integer uid){
        List<Article> articles = articleService.selectByUserId(uid);
        return articles;
    }

    @ApiOperation("根据文章id渲染文章")
    @RequestMapping(value = {"/show/{id}"},method = {RequestMethod.GET})
    public Article showArticle(@PathVariable Integer id){
        return articleService.showArticle(id);
    }

    @ApiOperation("查询所有的文章")
    @RequestMapping(value = {"/selectAllArticle"},method = {RequestMethod.GET})
    public List<Article> selectAllArticle(){
        return articleService.selectAllArticle();
    }

    @ApiOperation(value = "查询所有的用户")
    @RequestMapping(value = {"/selectAllUser"},method = {RequestMethod.GET})
    public List<Integer> selectAllUser(){
        return articleService.selectAllUser();
    }

    @ApiOperation(value = "上传图片接口")
    @RequestMapping(value = {"/uploading"},method = {RequestMethod.POST})
    public void uploadImage(MultipartFile file, HttpServletRequest req) throws IOException {
        File file1 = new File("D:\\img");
        file.transferTo(file1);
        // TODO: 2021/12/16 理解什么是相对路径和绝对路径？
        //return new RespMsg("上传失败");
    }
}







