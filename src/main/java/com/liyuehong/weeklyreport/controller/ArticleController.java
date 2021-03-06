package com.liyuehong.weeklyreport.controller;

import com.liyuehong.weeklyreport.model.Article;
import com.liyuehong.weeklyreport.model.Image;
import com.liyuehong.weeklyreport.model.User;
import com.liyuehong.weeklyreport.service.ArticleService;
import com.liyuehong.weeklyreport.service.UserService;
import com.liyuehong.weeklyreport.utils.RespMsg;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sun.misc.BASE64Decoder;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.*;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
@ControllerAdvice
public class ArticleController {


    /**
     * @description UserController.class：获取Class对象
     * @author yhli3
     * @updateTime 2022/2/26 15:14
     */
    final static Logger logger = LoggerFactory.getLogger(ArticleController.class);
    @Autowired
    ArticleService articleService;

    @Autowired
    UserService userService;

    @ApiOperation("根据文章的id删除文章")
    @ApiImplicitParam(name = "id",value = "文章id",required = true,dataType = "int")
    @DeleteMapping("/delete")
    public RespMsg deleteArticleById(Integer id){
        int i = articleService.deleteArticleById(id);
        if(i==1){
            return new RespMsg("true","文章删除成功！");
        }
        return new RespMsg("false","文章删除失败！");
    }

    @ApiOperation("更新文章")
    @PutMapping("/updateArticle")
    public Article updateArticle(@RequestBody Article article){
        return articleService.updateArticle(article);
    }

    @ApiOperation("创建文章")
    @PostMapping("/add")
    public RespMsg addNewArticle(@RequestBody Article article) {
        logger.info(article.toString());
        if(articleService.addNewArticle(article)==1){
            return new RespMsg("success",article.getId()+"");
        }
        return new RespMsg("error","文章提交失败，请联系管理员！");
    }

    @ApiImplicitParam(name = "time",value = "时间",required = true,dataType = "Date",defaultValue = "2022-02-10")
    @ApiOperation("查询本周周报接口")
    @GetMapping("/selectAllReports")
    public List<Article> selectByWeek(Date time){
        logger.debug(String.valueOf(time));
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
    @ApiImplicitParam(name = "uid",value = "用户id",required = true,dataType = "int")
    @GetMapping("/select/{uid}")
    public List<Article> selectByUserId(@PathVariable Integer uid){
        List<Article> articles = articleService.selectByUserId(uid);
        return articles;
    }

    @ApiOperation("根据文章id渲染文章")
    @GetMapping("/show/{id}")
    public Article showArticle(@PathVariable Integer id){
        logger.info("id:{}",id);
        return articleService.showArticle(id);
    }

    @ApiOperation("查询所有的文章")
    @GetMapping("/selectAllArticle")
    public List<Article> selectAllArticle(){
        return articleService.selectAllArticle();
    }

    // TODO: 2022/2/13 怎样判定图片是用于文章内部还是用于用户头像？
    @ApiOperation(value = "上传图片接口，返回图片名")
    @PostMapping(value = {"/uploading"})
    public RespMsg uploadImage(@RequestBody Image image, HttpServletRequest req){
        String base64Data = image.getData();
        //如果id为null，则图片用于写周报，id不为null，则用于用户头像
        Integer id = image.getId();
        //自定义日期格式
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
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
            suffix = ".jpeg";
        }else if("data:image/x-icon;".equalsIgnoreCase(dataPre)){
            //data:image/x-icon;base64,base64编码的icon图片数据
            suffix = ".ico";
        }else if("data:image/gif;".equalsIgnoreCase(dataPre)){
            //data:image/gif;base64,base64编码的gif图片数据
            suffix = ".gif";
        }else if("data:image/png;".equalsIgnoreCase(dataPre)){
            //data:image/png;base64,base64编码的png图片数据
            suffix = ".png";
        }else if("data:image/jpg;".equalsIgnoreCase(dataPre)){
            //data:image/jpg;base64,base64编码的png图片数据
            suffix = ".jpg";
        }else {
            return new RespMsg("error","上传图片格式不合法");
        }
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        //使用uuid给文件重命名
        String tempFileName=dtf.format(LocalDateTime.now())+uuid+suffix;
        if(id!=null){
            int i = userService.updateAvatarById(tempFileName,id);
        }
        //文件保存的地址
        // TODO: 2022/3/2 yhli3: 项目部署到Linux服务器上后记得改图片上传的保存地址
        //String imgFilePath = "D:\\article\\images\\"+tempFileName;
        String imgFilePath = "/home/ubuntu/weeksys/images/"+tempFileName;
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
            //url.append(req.getScheme())
            //        .append("://")
            //        .append(req.getServerName())
            //        .append(":")
            //        .append(req.getServerPort())
            //        .append(req.getContextPath())
            //        .append("/article/images/")
            //        .append(tempFileName);
            //return new RespMsg("success",url.toString());
            return new RespMsg("success",tempFileName);
        } catch (IOException e) {
            e.printStackTrace();
            return new RespMsg("error","上传图片失败");
        }
    }

    @ApiOperation(value = "通过传入图片名显示图片")
    @GetMapping(value = "/image/{imageName}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> getImage(@PathVariable("imageName") String imageName) throws Exception{
        byte[] imageContent;
        //String path = "D:\\article\\images\\" + imageName;
        String path = "/home/ubuntu/weeksys/images/" + imageName;
        imageContent = fileToByte(new File(path));

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.IMAGE_PNG);
        return new ResponseEntity<>(imageContent, headers, HttpStatus.OK);
    }

    public static byte[] fileToByte(File img) throws Exception {
        byte[] bytes = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            BufferedImage bi;
            bi = ImageIO.read(img);
            ImageIO.write(bi, "png", baos);
            bytes = baos.toByteArray();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            baos.close();
        }
        return bytes;
    }

}







