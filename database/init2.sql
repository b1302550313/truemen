-- MySQL dump 10.13  Distrib 8.0.36, for Win64 (x86_64)
--
-- Host: localhost    Database: verto
-- ------------------------------------------------------
-- Server version	8.0.36

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
  PRIMARY KEY (`uid`) USING BTREE,
  CONSTRAINT `userbaseinfo_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usercoreinfo` (`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userbaseinfo`
--

LOCK TABLES `userbaseinfo` WRITE;
/*!40000 ALTER TABLE `userbaseinfo` DISABLE KEYS */;
INSERT INTO `userbaseinfo` VALUES (1,'Alice','avatar_01.png','女','1990-01-01','Hello, I am Alice.'),(2,'Bob','avatar_02.png','男','1985-05-15','Hi there, I am Bob.'),(3,'Charlie','avatar_03.png','男','1992-08-20','Charlie here.'),(22,'张三',NULL,'匿',NULL,NULL);
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
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `usercoreinfo`
--

LOCK TABLES `usercoreinfo` WRITE;
/*!40000 ALTER TABLE `usercoreinfo` DISABLE KEYS */;
INSERT INTO `usercoreinfo` VALUES (1,'user123','12345678901','wx_user123','encrypted_password_1','1','2024-09-15 13:42:26'),(2,'user456','09876543211','wx_user456','encrypted_password_2','2','2024-09-15 13:42:26'),(3,'user789','11223344551',NULL,'encrypted_password_3','1','2024-09-15 13:42:26'),(22,'user_1726683471040','13012340008',NULL,'$2a$10$FcF0SsoisITRc6tiqBhPROoMB/YNan047beJiyM712xFELWh1aaLC','0','2024-09-18 18:17:51');
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

-- Dump completed on 2024-09-19  2:33:11
