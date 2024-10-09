-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: verto
-- ------------------------------------------------------
-- Server version	8.0.36

drop schema if exists verto;
create schema verto;
use verto;

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `bulletscreen`
--

DROP TABLE IF EXISTS `bulletscreen`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bulletscreen` (
  `bulletId` bigint NOT NULL AUTO_INCREMENT COMMENT '弹幕ID',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `content` varchar(5000) NOT NULL COMMENT '弹幕内容',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `visibility` int NOT NULL COMMENT '对谁可见，0所有人，1朋友可见，2仅自己可见',
  `duration` int NOT NULL COMMENT '0永久，1一年，2一月，3一天，4一小时',
  `contactInfo` varchar(200) DEFAULT NULL COMMENT '联系方式，逗号隔开，依次为手机；qq;微信',
  PRIMARY KEY (`bulletId`),
  KEY `uid` (`uid`),
  CONSTRAINT `bulletscreen_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usercoreinfo` (`uid`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bulletscreen`
--

LOCK TABLES `bulletscreen` WRITE;
/*!40000 ALTER TABLE `bulletscreen` DISABLE KEYS */;
/*!40000 ALTER TABLE `bulletscreen` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `bulletscreenlike`
--

DROP TABLE IF EXISTS `bulletscreenlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `bulletscreenlike` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `bulletId` bigint NOT NULL,
  `userId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `bulletId` (`bulletId`),
  KEY `userId` (`userId`),
  CONSTRAINT `bulletscreenlike_ibfk_1` FOREIGN KEY (`bulletId`) REFERENCES `bulletscreen` (`bulletId`),
  CONSTRAINT `bulletscreenlike_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `usercoreinfo` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bulletscreenlike`
--

LOCK TABLES `bulletscreenlike` WRITE;
/*!40000 ALTER TABLE `bulletscreenlike` DISABLE KEYS */;
/*!40000 ALTER TABLE `bulletscreenlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `chatrecord`
--

DROP TABLE IF EXISTS `chatrecord`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `chatrecord` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `userId` bigint DEFAULT NULL COMMENT '自己ID',
  `friendId` bigint DEFAULT NULL COMMENT '好友ID',
  `msgContent` varchar(512) DEFAULT NULL COMMENT '消息内容',
  `msgDate` timestamp NULL DEFAULT NULL COMMENT '消息时间',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `talkType` int DEFAULT NULL,
  `msgType` int DEFAULT '0',
  PRIMARY KEY (`id`),
  KEY `userId` (`userId`),
  KEY `friendId` (`friendId`),
  CONSTRAINT `chatrecord_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `usercoreinfo` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `chatrecord_ibfk_2` FOREIGN KEY (`friendId`) REFERENCES `usercoreinfo` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chatrecord`
--

LOCK TABLES `chatrecord` WRITE;
/*!40000 ALTER TABLE `chatrecord` DISABLE KEYS */;
INSERT INTO `chatrecord` VALUES (1,2,1,'Test2to1','2024-02-23 03:33:37','2024-02-23 03:33:37','2024-02-23 03:33:37',0,0),(2,3,1,'Test3to1','2024-02-23 03:33:49','2024-02-23 03:33:49','2024-02-23 03:33:49',0,0),(3,2,3,'Test2to3','2024-02-23 03:33:58','2024-02-23 03:33:58','2024-02-23 03:33:58',0,0);
/*!40000 ALTER TABLE `chatrecord` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `comment` (
  `commentId` bigint NOT NULL AUTO_INCREMENT COMMENT '评论ID',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `postId` bigint NOT NULL COMMENT '帖子ID',
  `content` varchar(200) NOT NULL COMMENT '评论内容',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  PRIMARY KEY (`commentId`),
  KEY `uid` (`uid`),
  KEY `postId` (`postId`),
  CONSTRAINT `comment_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usercoreinfo` (`uid`) ON UPDATE CASCADE,
  CONSTRAINT `comment_ibfk_2` FOREIGN KEY (`postId`) REFERENCES `post` (`postId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` (`uid`, `postId`, `content`) VALUES
(2, 1, '这真是一个有趣的观点！'),
(3, 3, '我完全同意你的看法。'),
(2, 2, '可以提供更多细节吗？我很感兴趣。'),
(3, 3, '很有见地的文章，感谢分享！'),
(2, 1, '我觉得还可以从另一个角度来探讨这个问题。'),
(2, 3, '这篇帖子让我想起了之前的一个项目经历。'),
(2, 2, '学习了，谢谢作者！'),
(1, 3, '有些地方还不太明白，可以再解释一下吗？'),
(3, 3, '这个主题一直是我关注的重点。'),
(3, 4, '期待更多类似的文章。');
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `groups`
--

DROP TABLE IF EXISTS `groups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `groupId` varchar(9) DEFAULT NULL COMMENT '群组ID',
  `groupName` varchar(16) DEFAULT NULL COMMENT '群组名称',
  `groupHead` varchar(16) DEFAULT NULL COMMENT '群组头像',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idxGroupId` (`groupId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `groups`
--

LOCK TABLES `groups` WRITE;
/*!40000 ALTER TABLE `groups` DISABLE KEYS */;
INSERT INTO `groups` VALUES (1,'5307397','楚们交流群','group_1','2023-12-31 16:00:00','2023-12-31 16:00:00');
/*!40000 ALTER TABLE `groups` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `landmark`
--

DROP TABLE IF EXISTS `landmark`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `landmark` (
  `landmarkId` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` text,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(100) DEFAULT NULL,
  `province` varchar(100) DEFAULT NULL,
  `country` varchar(100) DEFAULT NULL,
  `latitude` decimal(9,6) DEFAULT NULL,
  `longitude` decimal(9,6) DEFAULT NULL,
  `category` int DEFAULT NULL,
  PRIMARY KEY (`landmarkId`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `landmark`
--

LOCK TABLES `landmark` WRITE;
/*!40000 ALTER TABLE `landmark` DISABLE KEYS */;
INSERT INTO `landmark` VALUES (1,'怀士堂','中山大学南校园内的历史建筑','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129100,113.281700,1),(2,'马丁堂','中山大学南校园内的历史建筑','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129200,113.281900,2),(3,'梁銶琚堂','中山大学南校园内的重要建筑','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129300,113.282100,3),(4,'陈嘉庚堂','中山大学南校园内的标志性建筑','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129400,113.282300,4),(5,'孙中山铜像','中山大学南校园内的纪念性雕塑','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129500,113.282500,5),(6,'图书馆','中山大学南校园内的学术中心','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129600,113.282700,6),(7,'第一教学楼','中山大学南校园内的主要教学楼','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129700,113.282900,7),(8,'第二教学楼','中山大学南校园内的重要教学楼','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129800,113.283100,8),(9,'第三教学楼','中山大学南校园内的教学楼','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.129900,113.283300,9),(10,'体育馆','中山大学南校园内的运动场所','广州市海珠区新港西路135号中山大学南校园内','广州','广东','中国',23.130000,113.283500,10);
/*!40000 ALTER TABLE `landmark` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `post`
--
DROP TABLE IF EXISTS `guides`;
CREATE TABLE guides (
     id INT AUTO_INCREMENT PRIMARY KEY, -- 自增主键
     title VARCHAR(255) NOT NULL,       -- 攻略名称
     description TEXT NOT NULL,          -- 攻略介绍
     type VARCHAR(50) NOT NULL,          -- 攻略类型
     created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP -- 创建时间
);

DROP TABLE IF EXISTS `post`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `post` (
  `postId` bigint NOT NULL AUTO_INCREMENT COMMENT '帖子ID',
  `landmarkId` bigint NOT NULL COMMENT '地标id',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `title` varchar(100) NOT NULL COMMENT '帖子标题',
  `content` varchar(5000) NOT NULL COMMENT '帖子内容',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `tag` int DEFAULT NULL COMMENT '标签，只选一个,对应0123',
  `visibility` int NOT NULL COMMENT '对谁可见，0所有人，1朋友可见，2仅自己可见',
  `allowComment` int NOT NULL COMMENT '是否可评论，0可，1不可',
  `duration` int NOT NULL COMMENT '0永久，1一年，2一月，3一天，4一小时',
  `contactInfo` varchar(200) DEFAULT NULL COMMENT '联系方式，逗号隔开，依次为手机；qq;微信',
  PRIMARY KEY (`postId`),
  KEY `uid` (`uid`),
  KEY `landmarkId` (`landmarkId`),
  CONSTRAINT `post_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usercoreinfo` (`uid`) ON UPDATE CASCADE,
  CONSTRAINT `post_ibfk_2` FOREIGN KEY (`landmarkId`) REFERENCES `landmark` (`landmarkId`) ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `post`
--

LOCK TABLES `post` WRITE;
/*!40000 ALTER TABLE `post` DISABLE KEYS */;
INSERT INTO `post` VALUES (1,1,1,'探索宇宙的奥秘','宇宙是如此浩瀚，充满了无数的星系和未知的秘密。','2024-09-15 13:42:26',NULL,0,0,0,0,'13800138000;42;wechat_1'),(2,3,2,'健康生活每一天','保持健康的生活方式对于提高生活质量至关重要。','2024-09-15 13:42:26',NULL,1,0,1,1,'13800138001;3311;wechat_2'),(3,3,1,'编程的乐趣','编程不仅仅是一门技术，更是一种艺术和乐趣。','2024-09-15 13:42:26',NULL,3,2,0,2,'13800138002;123;wechat_3'),(4,2,2,'旅行的意义','旅行不仅仅是为了看风景，更是为了体验不同的文化和生活。','2024-09-15 13:42:26',NULL,1,1,1,3,'13800138003;123;wechat_4'),(5,4,3,'音乐的力量','音乐有着无法言喻的力量，能够触动人心，激发情感。','2024-09-15 13:42:26',NULL,2,0,0,4,'13800138004;123123;wechat_5'),(6,4,1,'摄影的艺术','摄影是一种捕捉瞬间，记录生活的艺术形式。','2024-09-15 13:42:26',NULL,1,2,1,0,'13800138005;4424;wechat_6'),(7,1,3,'阅读的乐趣','阅读能够开阔视野，丰富内心世界。','2024-09-15 13:42:26',NULL,3,1,0,1,'13800138006;123123;wechat_7'),(8,2,3,'科技改变生活','科技的发展正在不断地改变着我们的生活方式。','2024-09-15 13:42:26',NULL,1,2,1,2,'13800138007;123123;wechat_8'),(9,4,2,'环保的重要性','保护环境是每个人的责任，也是为了我们共同的未来。','2024-09-15 13:42:26',NULL,0,1,0,3,'13800138008;123123;wechat_9'),(10,3,1,'运动的好处','定期运动有助于保持身体健康，提高生活质量。','2024-09-15 13:42:26',NULL,0,1,1,4,'13800138009;123123;wechat_10');
/*!40000 ALTER TABLE `post` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postlike`
--

DROP TABLE IF EXISTS `postlike`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postlike` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `postId` bigint NOT NULL,
  `userId` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `postId` (`postId`),
  KEY `userId` (`userId`),
  CONSTRAINT `postlike_ibfk_1` FOREIGN KEY (`postId`) REFERENCES `post` (`postId`),
  CONSTRAINT `postlike_ibfk_2` FOREIGN KEY (`userId`) REFERENCES `usercoreinfo` (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postlike`
--

LOCK TABLES `postlike` WRITE;
/*!40000 ALTER TABLE `postlike` DISABLE KEYS */;
INSERT INTO `postlike` VALUES (1,1,2),(2,1,3),(3,2,1),(4,2,1);
/*!40000 ALTER TABLE `postlike` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postmedia`
--

DROP TABLE IF EXISTS `postmedia`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postmedia` (
  `mediaId` bigint NOT NULL AUTO_INCREMENT COMMENT '资源id',
  `postId` bigint NOT NULL COMMENT '帖子id',
  `type` int NOT NULL COMMENT '资源类别，0图片，1音频，2视频',
  `mediaUrl` varchar(300) NOT NULL,
  `createTime` timestamp NOT NULL,
  PRIMARY KEY (`mediaId`),
  KEY `postId` (`postId`),
  CONSTRAINT `postmedia_ibfk_1` FOREIGN KEY (`postId`) REFERENCES `post` (`postId`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postmedia`
--

LOCK TABLES `postmedia` WRITE;
/*!40000 ALTER TABLE `postmedia` DISABLE KEYS */;
INSERT INTO `postmedia` VALUES (1,1,0,'http://example.com/image1.jpg','2024-09-15 13:42:26'),(2,1,1,'http://example.com/audio1.mp3','2024-09-15 13:42:26'),(3,2,2,'http://example.com/video1.mp4','2024-09-15 13:42:26'),(4,3,0,'http://example.com/image2.jpg','2024-09-15 13:42:26'),(5,3,1,'http://example.com/audio2.mp3','2024-09-15 13:42:26'),(6,4,2,'http://example.com/video2.mp4','2024-09-15 13:42:26'),(7,5,0,'http://example.com/image3.jpg','2024-09-15 13:42:26'),(8,5,1,'http://example.com/audio3.mp3','2024-09-15 13:42:26'),(9,6,2,'http://example.com/video3.mp4','2024-09-15 13:42:26'),(10,7,0,'http://example.com/image4.jpg','2024-09-15 13:42:26'),(11,7,1,'http://example.com/audio4.mp3','2024-09-15 13:42:26');
/*!40000 ALTER TABLE `postmedia` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `talkbox`
--

DROP TABLE IF EXISTS `talkbox`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `talkbox` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `userId` bigint DEFAULT NULL COMMENT '用户ID',
  `talkId` bigint DEFAULT NULL COMMENT '对话框ID(好友ID、群组ID)',
  `talkType` int DEFAULT NULL COMMENT '对话框类型；0好友、1群组',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idxTalkIdUserId` (`userId`,`talkId`),
  CONSTRAINT `talkbox_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `usercoreinfo` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `talkbox`
--

LOCK TABLES `talkbox` WRITE;
/*!40000 ALTER TABLE `talkbox` DISABLE KEYS */;
INSERT INTO `talkbox` VALUES (1,1,1,1,'2024-02-23 11:35:54','2024-02-23 11:35:54'),(2,1,2,0,'2024-02-23 11:36:03','2024-02-23 11:36:03'),(3,3,1,1,'2024-02-23 07:26:32','2024-02-23 07:26:32');
/*!40000 ALTER TABLE `talkbox` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userbaseinfo`
--

DROP TABLE IF EXISTS `userbaseinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userbaseinfo` (
  `uid` bigint NOT NULL COMMENT '系统内部用户ID',
  `userName` varchar(100) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名称',
  `avatar` varchar(255) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像URL',
  `gender` enum('男','女','匿') COLLATE utf8mb4_general_ci NOT NULL,
  `birthDate` date DEFAULT NULL COMMENT '生日',
  `bio` varchar(500) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户简介',
  `zodiac` varchar(12) COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`uid`) USING BTREE,
  CONSTRAINT `userbaseinfo_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usercoreinfo` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userbaseinfo`
--

LOCK TABLES `userbaseinfo` WRITE;
/*!40000 ALTER TABLE `userbaseinfo` DISABLE KEYS */;
INSERT INTO `userbaseinfo` VALUES (1,'Alice','avatar_01.png','女','1990-01-01','Hello, I am Alice.',NULL),(2,'Bob','avatar_02.png','男','1985-05-15','Hi there, I am Bob.',NULL),(3,'Charlie','avatar_03.png','男','1992-08-20','Charlie here.',NULL),(22,'张三',NULL,'匿',NULL,NULL,NULL),(23,'李四',NULL,'匿',NULL,NULL,NULL),(24,'新用户',NULL,'匿',NULL,NULL,NULL),(25,'李四',NULL,'匿',NULL,NULL,NULL),(26,'李四1',NULL,'匿',NULL,NULL,NULL),(27,'李四2',NULL,'匿',NULL,NULL,NULL),(28,'新用户',NULL,'匿',NULL,NULL,NULL),(29,'新用户',NULL,'匿',NULL,NULL,NULL),(30,'新用户',NULL,'匿',NULL,NULL,NULL),(31,'新用户',NULL,'匿',NULL,NULL,NULL),(32,'新用户',NULL,'匿',NULL,NULL,NULL),(33,'新用户',NULL,'匿',NULL,NULL,NULL),(34,'新用户',NULL,'匿',NULL,NULL,NULL),(35,'新用户',NULL,'匿',NULL,NULL,NULL),(36,'新用户',NULL,'匿',NULL,NULL,NULL),(37,'新用户0',NULL,'匿','2007-01-04','关于我',NULL),(38,'新用户0',NULL,'匿','2007-01-04','关于我',NULL),(39,'新用户1',NULL,'匿','2007-01-04','关于我',NULL),(40,'李四2',NULL,'匿',NULL,NULL,NULL),(41,'新用户1',NULL,'匿','2007-01-04','关于我',NULL),(42,'张三',NULL,'匿',NULL,NULL,NULL),(43,'赵六',NULL,'匿',NULL,NULL,NULL),(44,'赵六',NULL,'匿',NULL,NULL,NULL),(45,'赵六',NULL,'匿',NULL,NULL,NULL),(46,'赵六',NULL,'匿',NULL,NULL,NULL),(47,'小12明',NULL,'女','2013-08-05','我是谁',NULL),(48,'赵六',NULL,'匿',NULL,NULL,NULL),(49,'新用户1',NULL,'匿','2007-01-04','关于我',NULL),(50,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(51,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(52,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(53,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(54,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(55,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(56,'赵六',NULL,'匿',NULL,NULL,NULL),(57,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(58,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(59,'我是新用户',NULL,'匿','2007-01-01','关于我',NULL),(60,'我是新用户1112',NULL,'匿','2000-01-01','我的新自我介绍',NULL),(61,'你的2名字',NULL,'女','2001-02-04','hh',NULL),(62,'小白',NULL,'女','2000-02-04','自我介绍',NULL),(63,'我',NULL,'女','2000-02-01','我的新自我介绍',NULL),(64,'我是',NULL,'女','2000-04-01','我的新自我介绍',NULL);
/*!40000 ALTER TABLE `userbaseinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usercoreinfo`
--

DROP TABLE IF EXISTS `usercoreinfo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usercoreinfo` (
  `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '系统内部用户ID',
  `userId` varchar(50) COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户可修改的唯一ID',
  `phone` varchar(20) COLLATE utf8mb4_general_ci NOT NULL COMMENT '手机号',
  `wechatId` varchar(50) COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '微信ID',
  `password` varchar(255) COLLATE utf8mb4_general_ci NOT NULL COMMENT '加密后的密码',
  `permission` enum('0','1','2') COLLATE utf8mb4_general_ci NOT NULL DEFAULT '0' COMMENT '用户权限值，0,1,2',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `wechatId` (`wechatId`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercoreinfo`
--

LOCK TABLES `usercoreinfo` WRITE;
/*!40000 ALTER TABLE `usercoreinfo` DISABLE KEYS */;
INSERT INTO `usercoreinfo` VALUES (1,'user123','12345678901','wx_user123','encrypted_password_1','1','2024-09-15 13:42:26'),(2,'user456','09876543211','wx_user456','encrypted_password_2','2','2024-09-15 13:42:26'),(3,'user789','11223344551',NULL,'encrypted_password_3','1','2024-09-15 13:42:26'),(22,'user_1726683471040','13012340008',NULL,'$2a$10$FcF0SsoisITRc6tiqBhPROoMB/YNan047beJiyM712xFELWh1aaLC','0','2024-09-18 18:17:51'),(23,'user_1726684581102','13012340009',NULL,'$2a$10$Y14h0WHpqMVgcXLrUd8Er.jxk0CmwIheBrAPP5rFUFXAMTdpefr.2','0','2024-09-18 18:36:21'),(24,'user_1726700463545','13512341200',NULL,'$2a$10$DuwukJfkw.aEjoOdwm/iA.iyaBLuaC//ATQ9LS3vahLqyZD3qfo4W','0','2024-09-18 23:01:04'),(25,'user_1726701767843','13012340010',NULL,'$2a$10$jFYTRDNSerGnzPI2fyH56OVDfoJ6RNaIi0zAXRwYXGJgV6yy3lguS','0','2024-09-18 23:22:48'),(26,'user_1726702437169','13012340011',NULL,'$2a$10$VlOK09OVsYerR4FqXrwCrO1OpvvPTkvnjFp.ZVDMchIcFFOd5MtAm','0','2024-09-18 23:33:57'),(27,'user_1726702956493','13012340012',NULL,'$2a$10$IFMbDJ2gh1lOvPYEu/XrM.Q8XkWzSG4SXfVuMkwdla1i4wzFJzAs6','0','2024-09-18 23:42:36'),(28,'user_1726703078471','13712341111',NULL,'$2a$10$BQ6MIp83tnRYmJFmc/zId.5s0NkAHcpGklQa10SE1LJ.bVYY.Lo5.','0','2024-09-18 23:44:38'),(29,'user_1726703482526','13512341212',NULL,'$2a$10$6v70O/oOuq7Bw2Q9od2vaOG9gCV0kHi/jkkotPj/m0qPV.XlSAVJu','0','2024-09-18 23:51:23'),(30,'user_1726703660387','13512341111',NULL,'$2a$10$UzMpbJwtenk8IkBgVeTEXOtpBNPVjc8wIjk4FLFYDXrbNofvDLHYu','0','2024-09-18 23:54:20'),(31,'user_1726703860459','13512342222',NULL,'$2a$10$p7RyxZQrDJDMGA7nsukmOefXO2.H2ZQWa3m53slzCdsFd1561C.9G','0','2024-09-18 23:57:40'),(32,'user_1726703914991','13512343333',NULL,'$2a$10$KcckgCEKdJ0JkvYnpSy6EuQ78B73ZzPBV9JyK3tcNg/NOJZKQ/bIC','0','2024-09-18 23:58:35'),(33,'user_1726704298609','13512344444',NULL,'$2a$10$vVOmi4U8gi7m8comrv0WfO2WmPfBIKDYXbv/vA0CX6fsNo1VXxSka','0','2024-09-19 00:04:59'),(34,'user_1726704593897','13512341233',NULL,'$2a$10$TAkIEPDm.ryioXhIVpKl3eW.tg7jZahNlqnDDbIk3IPbve2.O42ni','0','2024-09-19 00:09:54'),(35,'user_1726704959744','13712342222',NULL,'$2a$10$3oE/8XyF1bJ2E9ivpeiA0.QjUSaT3mNAVDCnZSnCeTSE3Zf4N6H6S','0','2024-09-19 00:16:00'),(36,'user_1726705031998','13812341111',NULL,'$2a$10$Ad1IQk8cnzTGBdiclrUrKeZXcI2.DIPUlRzTBP2zH.4wZOV/geqi6','0','2024-09-19 00:17:12'),(37,'user_1726706608137','13712343333',NULL,'$2a$10$Z.mMhcnpH5sLfYFbdBaZXO.li16Qh1uRrze.zbupOBga6QFQhtJdS','0','2024-09-19 00:43:28'),(38,'user_1726706798932','13712344444',NULL,'$2a$10$PwKtcpDZSqARm3QC31AqpOvAxR0Pz0SoTrH92nRbFKhOJrYw9c7A.','0','2024-09-19 00:46:39'),(39,'user_1726706884189','13712345555',NULL,'$2a$10$.PO2xmoSBieMhZp2KVoplu9Yf1wiRKSGf6VJLa8jo14BQvgfSLUe.','0','2024-09-19 00:48:04'),(40,'user_1726707108080','13012340013',NULL,'$2a$10$palQAGM4.Sewhr4Dy7gwNevbFUfTTo626JV2..xe1wMQmoRzdt4pu','0','2024-09-19 00:51:48'),(41,'user_1726707161378','13612341111',NULL,'$2a$10$N71Sv5yj4QfSHAiwo44HiOY7DKCQTQPF.MpmqVCrWmesMv3M3ZkPO','0','2024-09-19 00:52:41'),(42,'user_1726874435083','13012340006',NULL,'$2a$10$jxFkvAF.IlD/ykg4ZBi5ROlg/rf8ibTFBp/5u/qc9jnvft7PQ3cHe','0','2024-09-20 23:20:35'),(43,'user_1726879934189','13012340099',NULL,'$2a$10$.o38k1dvxA.a6prF3oQjAuEYIv9Ilm4ebfpRZdxM082F/56NkdbgW','0','2024-09-21 00:52:14'),(44,'user_1726881251396','13012340098',NULL,'$2a$10$PBGuoxtan.BR0PxDV3jMEum24QeM6SV5UIw1weYNrhKmWQopXlnEq','0','2024-09-21 01:14:11'),(45,'user_1726881418003','13012340097',NULL,'$2a$10$g41LWo3wP3wnvlvv2tRdeuxK5nNempcsxl5STS5guklWOyY0lguG.','0','2024-09-21 01:16:58'),(46,'user_1726882001125','13012340091',NULL,'$2a$10$Wb9ZNImlVbKKNOprc9r5rOcaMxmNCWOMUVsz1mXOfdZxJO7kGXjam','0','2024-09-21 01:26:41'),(47,'user_1110','13900000000',NULL,'$2a$10$GX1bEQBYYSMV6ZkteEv/6eGqKStHRO9.pxIsJz5vaRWJqGyer8CAC','0','2024-09-21 01:34:54'),(48,'user_1726882831724','13012340090',NULL,'$2a$10$mJF6nuUh7UOqq6agsEhVLe5oHDH6PJjHtzCFU49GavlxQZt86KlGa','0','2024-09-21 01:40:32'),(49,'user_1726883229917','13900000002',NULL,'$2a$10$p.o86LZkRruXXpj4UAxb0uFUR3/bilQwnFIrsFw1P9Pgmde9bcpF2','0','2024-09-21 01:47:10'),(50,'user_1726883350058','13900000003',NULL,'$2a$10$cYA7yVHkT6yDSl.TK2iZY.X8Ak3AoGYnvaOCu9cyDlfk39VViSlD2','0','2024-09-21 01:49:10'),(51,'user_1726883642504','13900000004',NULL,'$2a$10$3v3iSHPcLuFcPmexXvo77eysrC26mbwH8x3a.wc8gvS.y/Js3OCDi','0','2024-09-21 01:54:03'),(52,'user_1726883751416','13900000005',NULL,'$2a$10$VAafAAIvQEBKFEs496dj8ecJ.viknzDktW0Rif8KHl9.Na/hGQiRe','0','2024-09-21 01:55:51'),(53,'user_1726908598365','13900000006',NULL,'$2a$10$s3X/CarBXrx7La4qksC7gu1rpVlg8isz88O8rl0Nb9iSkSgXsEx0q','0','2024-09-21 08:49:58'),(54,'user_1726908724118','13900000007',NULL,'$2a$10$W23S/pDGHc2KU2/1Wgz7oeYwPeF.0EL1G6c1hWCwYtrjxWFnUPINS','0','2024-09-21 08:52:04'),(55,'user_1726909017968','13900000009',NULL,'$2a$10$rzXZg7jiHAtEUVNxvaqMIOx6UNwumKnvO5qnqrbuyTti3T.Lk6ceO','0','2024-09-21 08:56:58'),(56,'user_1726909428601','13900000010',NULL,'$2a$10$zT6158sgP.pg3LaTZ71Ae.tkNLIIpakBkR3m4wusiNmds2gr6Xnnu','0','2024-09-21 09:03:49'),(57,'user_1726909493440','13900000011',NULL,'$2a$10$hLJld147r7.zeByFqdkNxOao.Lo1xkLsj16UcyGeL37KOvqsAXyJG','0','2024-09-21 09:04:53'),(58,'user_1726909819042','13900000012',NULL,'$2a$10$5LSD7ARHL6t8N8kHBVaLbuYtj/JHeN64NLCFRi6BgF72FR.RgQ3a2','0','2024-09-21 09:10:19'),(59,'user_1726910303074','13900000013',NULL,'$2a$10$Aw1qFMX0GCGJ3lTP8UaZWOTvBy0bZs3LL0HbduDorV3D3kOMhyLbq','0','2024-09-21 09:18:23'),(60,'user_1726915038039','13900000014',NULL,'$2a$10$cVqyGwmg9kZ7WVOeyYjsWO6VZgsYUFiAXVSv6dU4USyMFoSY8kBIK','0','2024-09-21 10:37:18'),(61,'user_1726915607618','13900000015',NULL,'$2a$10$dR33KgjsdEsRMpyc.xIzNuBStoyfTg/ckMfU5LzMtONQo8mwlHMjy','0','2024-09-21 10:46:48'),(62,'user_1726917307037','13900000016',NULL,'$2a$10$Yaf7lxHdXpAZSiRV/j0FfeNv5.2pbsG1oWXApugzOrKbsb15cyrc2','0','2024-09-21 11:15:07'),(63,'user_1726917881459','13900000017',NULL,'$2a$10$4hYbKuVMPSJbNva04Ipm2.7HzQ2MLMDrTiFssxdvjWO04lFKBZO46','0','2024-09-21 11:24:41'),(64,'user_1726918327484','13900000018',NULL,'$2a$10$HQI32ctRGcYIv94IAMfbA.YoMSUlpCMNJZ3qDP1DfyDQPAyHaaj4W','0','2024-09-21 11:32:07');
/*!40000 ALTER TABLE `usercoreinfo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userfriend`
--

DROP TABLE IF EXISTS `userfriend`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `userfriend` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `userId` bigint DEFAULT NULL COMMENT '用户ID',
  `userFriendId` bigint DEFAULT NULL COMMENT '好友用户ID',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uuidIdx` (`userId`,`userFriendId`),
  KEY `userFriendId` (`userFriendId`),
  CONSTRAINT `userfriend_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `usercoreinfo` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userfriend_ibfk_2` FOREIGN KEY (`userFriendId`) REFERENCES `usercoreinfo` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userfriend`
--

LOCK TABLES `userfriend` WRITE;
/*!40000 ALTER TABLE `userfriend` DISABLE KEYS */;
INSERT INTO `userfriend` VALUES (1,1,2,'2024-02-23 05:02:45','2024-02-23 05:02:45'),(2,1,3,'2024-02-23 05:02:45','2024-02-23 05:02:45'),(3,2,3,'2024-02-23 05:02:45','2024-02-23 05:02:45');
/*!40000 ALTER TABLE `userfriend` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `usergroup`
--

DROP TABLE IF EXISTS `usergroup`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `usergroup` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `userId` bigint DEFAULT NULL COMMENT '用户ID',
  `groupId` varchar(9) DEFAULT NULL COMMENT '群组ID',
  `createTime` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idxUuid` (`userId`,`groupId`),
  CONSTRAINT `usergroup_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `usercoreinfo` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb3;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usergroup`
--

LOCK TABLES `usergroup` WRITE;
/*!40000 ALTER TABLE `usergroup` DISABLE KEYS */;
INSERT INTO `usergroup` VALUES (1,1,'5307397','2023-12-31 16:00:00','2023-12-31 16:00:00'),(2,2,'5307397','2023-12-31 16:00:00','2023-12-31 16:00:00'),(3,3,'5307397','2023-12-31 16:00:00','2023-12-31 16:00:00');
/*!40000 ALTER TABLE `usergroup` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-09-21 19:46:06
