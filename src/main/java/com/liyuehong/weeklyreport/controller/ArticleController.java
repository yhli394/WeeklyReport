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
import sun.misc.BASE64Decoder;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

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
    public RespMsg uploadImage(String base64Data,HttpServletRequest req){
        StringBuffer url = new StringBuffer();
        //位于base64字符前面的字符串（data:image/xxx;）
        String dataPre = "";
        //实体部分数据
        String data = "";
        if(base64Data==null||"".equals(base64Data)){
            return new RespMsg("error","上传失败，图片为空！");
        }else{
            //将字符串分隔成字符数组
            String [] d = base64Data.split("base64,");
            if(d != null && d.length == 2){
                dataPre = d[0];
                data = d[1];
            }else {
                return new RespMsg("error","上传失败，数据不合法");
            }
        }
        //图片后缀，用以识别哪种格式数据
        String suffix = "";
        //data:image/jpeg;base64,base64编码的jpeg图片数据
        if("data:image/jpeg;".equalsIgnoreCase(dataPre)){
            suffix = ".jpg";
        }else if("data:image/x-icon;".equalsIgnoreCase(dataPre)){
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        }else if("data:image/gif;".equalsIgnoreCase(dataPre)){
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        }else if("data:image/png;".equalsIgnoreCase(dataPre)){
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else {
            return new RespMsg("error","上传图片格式不合法");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //使用uuid给图片重命名
        String tempFileName=uuid+suffix;
        //新生成的图片
        String imgFilePath = "D:\\image\\"+tempFileName;
        BASE64Decoder decoder = new BASE64Decoder();
        try {
            //Base64解码
            byte[] b = decoder.decodeBuffer(data);
            for(int i=0;i<b.length;++i) {
                if(b[i]<0) {
                    //调整异常数据
                    b[i]+=256;
                }
            }
            OutputStream out = new FileOutputStream(imgFilePath);
            out.write(b);
            out.flush();
            out.close();
            //String imgurl="http://xxxxxxxx/"+tempFileName;
            url.append(req.getScheme())
                    .append("://")
                    .append(req.getServerName())
                    .append(":")
                    .append(req.getServerPort())
                    .append(req.getContextPath())
                    .append(imgFilePath);
            return new RespMsg("success",url.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return new RespMsg("error","上传图片失败");
        }
    }

}







