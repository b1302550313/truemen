
drop schema if exists verto;
create schema verto;
use verto;

DROP TABLE IF EXISTS `bulletscreen`;
CREATE TABLE `bulletscreen` (
  `bulletId` bigint NOT NULL AUTO_INCREMENT COMMENT '弹幕ID',
  `landmarkId` bigint NOT NULL COMMENT '地标ID',
  `uid` bigint NOT NULL COMMENT '用户ID',
  `content` varchar(5000) NOT NULL COMMENT '弹幕内容',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
  `updateTime` timestamp NULL DEFAULT NULL COMMENT '更新时间',
  `tag` int DEFAULT NULL COMMENT '标签，只选一个,对应0123',
  `visibility` int NOT NULL COMMENT '对谁可见，0所有人，1朋友可见，2仅自己可见',
  `allowComment` int NOT NULL COMMENT '是否可评论，0可，1不可',
  `duration` int NOT NULL COMMENT '0永久，1一年，2一月，3一天，4一小时',
  `contactInfo` varchar(200) DEFAULT NULL COMMENT '联系方式，逗号隔开，依次为手机；qq;微信',
  PRIMARY KEY (`bulletId`),
  KEY `uid` (`uid`),
  CONSTRAINT `bulletscreen_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `usercoreinfo` (`uid`) ON UPDATE CASCADE,
  CONSTRAINT `bulletscreen_ibfk_2` FOREIGN KEY (`landmarkId`) REFERENCES `landmark` (`landmarkId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

DROP TABLE IF EXISTS `bulletscreenlike`;
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

DROP TABLE IF EXISTS `chatrecord`;
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

DROP TABLE IF EXISTS `landmark`;
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

DROP TABLE IF EXISTS `post`;
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

DROP TABLE IF EXISTS `usercoreinfo`;
CREATE TABLE `usercoreinfo` (
  `uid` bigint NOT NULL AUTO_INCREMENT COMMENT '系统内部用户ID',
  `userId` varchar(50) NOT NULL COMMENT '用户可修改的唯一ID',
  `phone` varchar(20) NOT NULL COMMENT '手机号',
  `wechatId` varchar(50) DEFAULT NULL COMMENT '微信ID',
  `password` varchar(255) NOT NULL COMMENT '加密后的密码',
  `permission` enum('0','1','2') NOT NULL DEFAULT '0' COMMENT '用户权限值，0,1,2',
  `createTime` timestamp NULL DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间',
  PRIMARY KEY (`uid`),
  UNIQUE KEY `userId` (`userId`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `wechatId` (`wechatId`)
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

DROP TABLE IF EXISTS `userfriend`;
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

DROP TABLE IF EXISTS `usergroup`;
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


lock table `chatRecord` write;
INSERT INTO
    `chatRecord` (
        `userId`,
        `friendId`,
        `msgContent`,
        `msgDate`,
        `createTime`,
        `updateTime`,
        `talkType`,
        `msgType`
    )
VALUES
    (
        2,
        1,
        'Test2to1',
        '2024-02-23 11:33:37',
        '2024-02-23 11:33:37',
        '2024-02-23 11:33:37',
        0,
        0
    ),
    (
        3,
        1,
        'Test3to1',
        '2024-02-23 11:33:49',
        '2024-02-23 11:33:49',
        '2024-02-23 11:33:49',
        0,
        0
    ),
    (
        2,
        3,
        'Test2to3',
        '2024-02-23 11:33:58',
        '2024-02-23 11:33:58',
        '2024-02-23 11:33:58',
        0,
        0
    );
unlock tables;

lock table `groups` write;
INSERT INTO
    `groups` (
        `groupId`,
        `groupName`,
        `groupHead`,
        `createTime`,
        `updateTime`
    )
VALUES
    (
        '5307397',
        '楚们交流群',
        'group_1',
        '2024-01-01 00:00:00',
        '2024-01-01 00:00:00'
    );
unlock tables;

lock table `landmark` write;
INSERT INTO
    `landmark` (
        `name`,
        `description`,
        `address`,
        `city`,
        `province`,
        `country`,
        `latitude`,
        `longitude`,
        `category`
    )
VALUES
    (
        '怀士堂',
        '中山大学南校园内的历史建筑',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1291,
        113.2817,
        1
    ),
    (
        '马丁堂',
        '中山大学南校园内的历史建筑',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1292,
        113.2819,
        2
    ),
    (
        '梁銶琚堂',
        '中山大学南校园内的重要建筑',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1293,
        113.2821,
        3
    ),
    (
        '陈嘉庚堂',
        '中山大学南校园内的标志性建筑',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1294,
        113.2823,
        4
    ),
    (
        '孙中山铜像',
        '中山大学南校园内的纪念性雕塑',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1295,
        113.2825,
        5
    ),
    (
        '图书馆',
        '中山大学南校园内的学术中心',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1296,
        113.2827,
        6
    ),
    (
        '第一教学楼',
        '中山大学南校园内的主要教学楼',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1297,
        113.2829,
        7
    ),
    (
        '第二教学楼',
        '中山大学南校园内的重要教学楼',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1298,
        113.2831,
        8
    ),
    (
        '第三教学楼',
        '中山大学南校园内的教学楼',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1299,
        113.2833,
        9
    ),
    (
        '体育馆',
        '中山大学南校园内的运动场所',
        '广州市海珠区新港西路135号中山大学南校园内',
        '广州',
        '广东',
        '中国',
        23.1300,
        113.2835,
        10
    );
unlock tables;

lock table `post` write;
INSERT INTO
    `post` (
        `uid`,
        `landmarkId`,
        `title`,
        `content`,
        `createTime`,
        `updateTime`,
        `tag`,
        `visibility`,
        `allowComment`,
        `duration`,
        `contactInfo`
    )
VALUES
    (
        1,
        1,
        '探索宇宙的奥秘',
        '宇宙是如此浩瀚，充满了无数的星系和未知的秘密。',
        NOW(),
        NULL,
        0,
        0,
        0,
        0,
        '13800138000;42;wechat_1'
    ),
    (
        2,
        3,
        '健康生活每一天',
        '保持健康的生活方式对于提高生活质量至关重要。',
        NOW(),
        NULL,
        1,
        0,
        1,
        1,
        '13800138001;3311;wechat_2'
    ),
    (
        1,
        3,
        '编程的乐趣',
        '编程不仅仅是一门技术，更是一种艺术和乐趣。',
        NOW(),
        NULL,
        3,
        2,
        0,
        2,
        '13800138002;123;wechat_3'
    ),
    (
        2,
        2,
        '旅行的意义',
        '旅行不仅仅是为了看风景，更是为了体验不同的文化和生活。',
        NOW(),
        NULL,
        1,
        1,
        1,
        3,
        '13800138003;123;wechat_4'
    ),
    (
        3,
        4,
        '音乐的力量',
        '音乐有着无法言喻的力量，能够触动人心，激发情感。',
        NOW(),
        NULL,
        2,
        0,
        0,
        4,
        '13800138004;123123;wechat_5'
    ),
    (
        1,
        4,
        '摄影的艺术',
        '摄影是一种捕捉瞬间，记录生活的艺术形式。',
        NOW(),
        NULL,
        1,
        2,
        1,
        0,
        '13800138005;4424;wechat_6'
    ),
    (
        3,
        1,
        '阅读的乐趣',
        '阅读能够开阔视野，丰富内心世界。',
        NOW(),
        NULL,
        3,
        1,
        0,
        1,
        '13800138006;123123;wechat_7'
    ),
    (
        3,
        2,
        '科技改变生活',
        '科技的发展正在不断地改变着我们的生活方式。',
        NOW(),
        NULL,
        1,
        2,
        1,
        2,
        '13800138007;123123;wechat_8'
    ),
    (
        2,
        4,
        '环保的重要性',
        '保护环境是每个人的责任，也是为了我们共同的未来。',
        NOW(),
        NULL,
        0,
        1,
        0,
        3,
        '13800138008;123123;wechat_9'
    ),
    (
        1,
        3,
        '运动的好处',
        '定期运动有助于保持身体健康，提高生活质量。',
        NOW(),
        NULL,
        0,
        1,
        1,
        4,
        '13800138009;123123;wechat_10'
    );
unlock tables;

lock table `postLike` write;
INSERT INTO
    `postLike` (`postId`, `userId`)
VALUES
    (1, 2),
    (1, 3),
    (2, 1),
    (2, 1);
unlock tables;

lock table `postMedia` write;
INSERT INTO
    `postMedia` (`postId`, `type`, `mediaUrl`, `createTime`)
VALUES
    (1, 0, 'http://example.com/image1.jpg', NOW()),
    (1, 1, 'http://example.com/audio1.mp3', NOW()),
    (2, 2, 'http://example.com/video1.mp4', NOW()),
    (3, 0, 'http://example.com/image2.jpg', NOW()),
    (3, 1, 'http://example.com/audio2.mp3', NOW()),
    (4, 2, 'http://example.com/video2.mp4', NOW()),
    (5, 0, 'http://example.com/image3.jpg', NOW()),
    (5, 1, 'http://example.com/audio3.mp3', NOW()),
    (6, 2, 'http://example.com/video3.mp4', NOW()),
    (7, 0, 'http://example.com/image4.jpg', NOW()),
    (7, 1, 'http://example.com/audio4.mp3', NOW());
unlock tables;

lock table `talkBox` write;
INSERT INTO
    `talkBox` (
        `userId`,
        `talkId`,
        `talkType`,
        `createTime`,
        `updateTime`
    )
VALUES
    (
        1,
        1,
        1,
        '2024-02-23 19:35:54',
        '2024-02-23 19:35:54'
    ),
    (
        1,
        2,
        0,
        '2024-02-23 19:36:03',
        '2024-02-23 19:36:03'
    ),
    (
        3,
        1,
        1,
        '2024-02-23 15:26:32',
        '2024-02-23 15:26:32'
    );
unlock tables;

lock table `userBaseInfo` write;
INSERT INTO
    `userBaseInfo` (
        `uid`,
        `userName`,
        `avatar`,
        `gender`,
        `birthDate`,
        `bio`
    )
VALUES
    (
        1,
        'Alice',
        'avatar_01.png',
        '女',
        '1990-01-01',
        'Hello, I am Alice.'
    ),
    (
        2,
        'Bob',
        'avatar_02.png',
        '男',
        '1985-05-15',
        'Hi there, I am Bob.'
    ),
    (
        3,
        'Charlie',
        'avatar_03.png',
        '男',
        '1992-08-20',
        'Charlie here.'
    );
unlock tables;

lock table `userCoreInfo` write;
INSERT INTO
    `userCoreInfo` (
        `userId`,
        `phone`,
        `wechatId`,
        `password`,
        `permission`
    )
VALUES
    (
        'user123',
        '12345678901',
        'wx_user123',
        'encrypted_password_1',
        '1'
    ),
    (
        'user456',
        '09876543211',
        'wx_user456',
        'encrypted_password_2',
        '2'
    ),
    (
        'user789',
        '11223344551',
        NULL,
        'encrypted_password_3',
        '1'
    );
unlock tables;

lock table `userFriend` write;
INSERT INTO
    `userFriend` (
        `userId`,
        `userFriendId`,
        `createTime`,
        `updateTime`
    )
VALUES
    (
        1,
        2,
        '2024-02-23 13:02:45',
        '2024-02-23 13:02:45'
    ),
    (
        1,
        3,
        '2024-02-23 13:02:45',
        '2024-02-23 13:02:45'
    ),
    (
        2,
        3,
        '2024-02-23 13:02:45',
        '2024-02-23 13:02:45'
    );
unlock tables;

lock table `userGroup` write;
INSERT INTO
    `userGroup` (`userId`, `groupId`, `createTime`, `updateTime`)
VALUES
    (
        1,
        '5307397',
        '2024-01-01 00:00:00',
        '2024-01-01 00:00:00'
    ),
    (
        2,
        '5307397',
        '2024-01-01 00:00:00',
        '2024-01-01 00:00:00'
    ),
    (
        3,
        '5307397',
        '2024-01-01 00:00:00',
        '2024-01-01 00:00:00'
    );
unlock tables;;