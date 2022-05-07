/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 5.7.35 : Database - week_report
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`week_report` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `week_report`;

/*Table structure for table `article` */

DROP TABLE IF EXISTS `article`;

CREATE TABLE `article` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(64) DEFAULT NULL,
  `editDate` datetime DEFAULT NULL,
  `publishDate` datetime DEFAULT NULL,
  `uid` int(11) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `content` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8;

/*Data for the table `article` */

insert  into `article`(`id`,`title`,`editDate`,`publishDate`,`uid`,`state`,`content`) values (1,'string5888',NULL,'2022-03-15 16:10:49',1,NULL,'string11![Screenshot_20220124170819733_com.example.my.jpg](/api/article/image/2022-03-06d4291f63024443acb362219a9058d9ce.jpeg)![Snipaste_20220315_154330.png](http://localhost:8082/article/image/2022-03-152eeb1f84fda54ca0b255176edc6dfc66.png)'),(2,'111',NULL,'2022-03-06 14:43:53',1,NULL,'jhdgvjshg'),(3,'jyrde',NULL,'2022-03-06 16:12:37',6,NULL,'xs'),(4,'xxx',NULL,'2022-03-06 16:15:22',6,NULL,'![微信图片_20220302201634.png](/api/article/image/2022-03-066726275be2c9477eaa4bec97e0889024.png)'),(5,'11',NULL,'2022-03-06 17:20:06',13,NULL,'hgjh'),(6,'11',NULL,'2022-03-06 17:20:25',13,NULL,'hgjh![20200401090133663.jpeg](/api/article/image/2022-03-06e3856c9f2eef41549572deb751f8e436.jpeg)'),(7,'222',NULL,'2022-03-08 19:20:37',1,NULL,'![微信图片_20220302201617.png](/api/article/image/2022-03-06c48c5eb08b62494db9dd5b3414ff4a9a.png)'),(8,'45',NULL,'2022-03-08 19:21:17',1,NULL,'购房'),(9,'99',NULL,'2022-03-08 19:22:15',1,NULL,'99'),(10,'10',NULL,'2022-03-09 22:47:51',1,NULL,'fddgfvdsgdgdgdgdfgdfgdfg![2560x1440screenshot.png](/api/article/image/2022-03-09404db9f884914f4b9e882d190b2c8249.png)'),(11,'xxx的周报',NULL,'2022-03-09 22:53:11',1,NULL,'8他也很痛恨'),(12,'jjj的周报',NULL,'2022-03-10 16:08:52',1,NULL,'gsreggg\n![微信图片_20220306221354.jpg](/api/article/image/2022-03-10d53f94599de34c649ad8b6dba05134f9.jpeg)\nrjirfj '),(13,'jgjgyjh',NULL,'2022-03-10 16:33:20',1,NULL,'hcghchg\n\n\n![git提交日志_3a00172dae2f83657a2d362a38b78348.png](/api/article/image/2022-03-1031d9a3c888bf4aa89fda16f60594c7a9.png)'),(14,'iii的周报',NULL,'2022-04-07 17:16:11',1,NULL,'## 方法\n1.方法\n\n### 算法\n1. 威风威风\n\n\n\n![gcd.jpg](http://localhost:8082/article/image/2022-04-0745a52babf98c4c35b1f9bf3792c5ed46.jpeg)\n'),(15,'qqq的周报',NULL,'2022-05-07 14:55:25',1,NULL,'而股份分割\n\n\n![image.png](http://localhost:8082/article/image/2022-05-07f6761ef459f942bebef38a48fb1bc5a7.png)'),(16,'mmm的周报',NULL,'2022-05-07 15:29:12',1,NULL,'---\ntitle: xxx\ndate: 2022-5-7\n---\n\n## 本周周报\n1. 本周研究了xxx\n2. xxx\n3. ....\n\n## 下周计划\n1. 阅读xxx文献\n\n'),(17,'t4y4y4y',NULL,'2022-05-07 15:37:53',1,NULL,'egtg34g43g43g3');

/*Table structure for table `role` */

DROP TABLE IF EXISTS `role`;

CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(64) DEFAULT NULL,
  `nameZh` varchar(64) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

/*Data for the table `role` */

insert  into `role`(`id`,`name`,`nameZh`) values (1,'admin','超级管理员'),(2,'user','普通用户');

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(64) DEFAULT NULL,
  `password` varchar(64) DEFAULT NULL,
  `enabled` int(11) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `regTime` datetime DEFAULT NULL,
  `avatar_name` varchar(256) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `emailIndex` (`email`(6))
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

/*Data for the table `user` */

insert  into `user`(`id`,`username`,`password`,`enabled`,`email`,`regTime`,`avatar_name`) values (1,'admin','$2a$10$qx01CfXY0P0DQhr.lLmjP.xp7XeIFrnE3l8p4chLXJg0KoZTcREkq',1,NULL,NULL,'2022-03-09e4c0daa13dbd4392b95a7b62402deac7.png'),(9,'string','$2a$10$LIChXCPQB.1kmch7hXyamOmBro6LcK3oJN3usqkh4WvL2mhpp1q8K',1,'string','2022-03-06 17:09:30','2022-03-06ca922e80a760458a9ee35552581c45c9.png'),(10,'hyw','$2a$10$Zx3tJqme1vUGUH5KXgX5H.7SdoFTm44SFwhte9WrtyEOImf6t.Q7a',1,'1449582589@qq.com','2022-03-06 17:14:05','2022-03-06ca922e80a760458a9ee35552581c45c9.png'),(11,'hyw11','$2a$10$EAEUmi57V8g5egHWgl3A2eGiBeEguoZPu6acgA5Yg6FQRRBeWpPwG',1,'1449582589@qq.com','2022-03-06 17:14:26','2022-03-06ca922e80a760458a9ee35552581c45c9.png'),(12,'lili','$2a$10$BHh5PzCddaD34Lav6NXguOu2afs6bDlPP04EaqiqoHqtPm8bRGeBG',1,'thcyhtyhh','2022-03-06 17:16:40','2022-03-06ca922e80a760458a9ee35552581c45c9.png'),(13,'11','$2a$10$SsjDkptF4c8pYZPeOfJeAOM8JmQ0xvx77CvUaQ57gBYBZrNauksVq',1,'guzheng16399@163.com','2022-03-06 17:19:01','2022-03-06ca922e80a760458a9ee35552581c45c9.png'),(14,'liyuehong','$2a$10$FNBYAB8OyecTesJxhLoGYeEPnCGCLyDZiMlMRacYpxGtjnP8/yj2.',1,'liyuehong@my.swjtu.edu.cn','2022-03-15 20:11:58','2022-03-15fed36e42102642e4a2045717763a02e8.png'),(15,'小李','$2a$10$vyG2nIN/n3zBWvDm3pMvI.Wz9AvolEG.fuSW3gbm/xYYkgjVeaELK',1,'46468ge','2022-05-07 15:26:20','2022-03-06ca922e80a760458a9ee35552581c45c9.png');

/*Table structure for table `user_role` */

DROP TABLE IF EXISTS `user_role`;

CREATE TABLE `user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

/*Data for the table `user_role` */

insert  into `user_role`(`id`,`user_id`,`role_id`) values (1,1,1),(2,1,2),(3,1,3),(4,4,2),(5,5,2),(6,6,2),(7,7,2),(8,8,2),(9,9,2),(11,11,2),(12,12,2),(13,13,2),(14,10,1),(15,14,2),(16,15,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
