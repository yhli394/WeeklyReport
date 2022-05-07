---
title: 周报系统
date: 2022-5-7
---

## 项目简介

研究生阶段，不同导师基本上都有定期开组会的习惯，且有些导师会要求每个同学做PPT汇报最近的研究进展。本人所在的课题组每周会开组会，于是在师兄的指导下和本组的师弟合作开发了一套前后端分离的周报系统。

## 项目效果图

### 前台管理系统

登录界面如下：
![20220507155128-2022-05-07-15-51-29](https://images-1309978559.cos.ap-chengdu.myqcloud.com/blogimages/20220507155128-2022-05-07-15-51-29.png)

点击登录按钮后会进入到如下的界面，本周周报栏会渲染一周的周报，点击右边的展开所有或者＞按钮，会展开所有用户或者单个用户写的详细内容。

![20220507155333-2022-05-07-15-53-33](https://images-1309978559.cos.ap-chengdu.myqcloud.com/blogimages/20220507155333-2022-05-07-15-53-33.png)

![20220507155951-2022-05-07-15-59-52](https://images-1309978559.cos.ap-chengdu.myqcloud.com/blogimages/20220507155951-2022-05-07-15-59-52.png)

除此之外，还有各人周报、编写、选择周等功能，这里就不再介绍了。

### 后台管理系统

登录界面：
![20220507160519-2022-05-07-16-05-20](https://images-1309978559.cos.ap-chengdu.myqcloud.com/blogimages/20220507160519-2022-05-07-16-05-20.png)

创建周报面板：

![20220507161719-2022-05-07-16-17-20](https://images-1309978559.cos.ap-chengdu.myqcloud.com/blogimages/20220507161719-2022-05-07-16-17-20.png)

角色管理面板：

![20220507161816-2022-05-07-16-18-17](https://images-1309978559.cos.ap-chengdu.myqcloud.com/blogimages/20220507161816-2022-05-07-16-18-17.png)

## 技术栈

前端：Vue、axios、ElementUI、vue-router等
后端：SpringBoot、SpringSecurity、Mybatis、MySQL、Redis等

## 项目本地运行

- git clone地址：

> git@github.com:yhli394/WeeklyReport.git

- 在IDE中打开克隆好的文件（推荐用JetBrains公司开发的IntelliJ IDEA打开），并在src\main\resources目录下，找到数据库文件week.sql，导入你自己的数据库，然后在application.yml或者application.properties中进行相应的配置，可以参考以下的application.yml配置文件进行配置：

```yml
spring:
  datasource: # 配置数据库
    type: com.alibaba.druid.pool.DruidDataSource
    url: xxx
    username: xxx
    password: xxx

  redis: # 配置redis
    host: xxx
    port: xxx
    password: xxx


server:
  port: 8088 # 配置端口号
  max-http-header-size: 10MB # 配置请求头的大小
  servlet:
    session:
      timeout: 1800 # 配置session过期时间，（使用@EnableRedisHttpSession注解，server.servlet.session.timeout=1800会失效）

logging:
  config: classpath:logback.xml # 设置项目启动后读取的日志配置文件
  level: # logging.level用来指定具体的包或类下的日志的输出级别
    com:
      liyuehong:
        weeklyreport:
          mapper: trace


# 跨域集合配置(允许请求的列表)
cross:
  crossLists:
    - "http://localhost:9529"
    - "http://localhost:8081"
    - "http://localhost:8082"
    - "http://192.168.241.58:8081"
    - "http://47.101.198.23:80"
    - "http://localhost:8086"
    - "http://124.221.171.100:80"
    - "http://localhost:8085"
    - "http://localhost:9528"
```

- 解压VG-manageSystem.zip（后台管理系统源文件）和WBlog.zip（前台管理系统源文件）这两个文件，在IDE中分别打开这两个文件（推荐用WebStorm打开）

- 启动后端和前端（前端打开后需要先安装相应的依赖，使用npm install或者yarn install）就可以在本地查看效果啦！

## 小结

鉴于本人能力有限，系统设计和代码实现方面难免存在不足，如有不足的地方欢迎指出来。欢迎大家Star！