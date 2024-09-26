DROP DATABASE IF EXISTS `verto`;

CREATE DATABASE `verto` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

USE `verto`;

-- 用户核心信息表
CREATE TABLE `userCoreInfo` (
    `uid` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '系统内部用户ID',
    -- 系统生成的唯一标识符，不可修改
    `userId` VARCHAR(50) UNIQUE NOT NULL COMMENT '用户可修改的唯一ID',
    -- 用户唯一标识，可修改
    `phone` VARCHAR(20) UNIQUE NOT NULL COMMENT '手机号',
    -- 用户手机号，登录使用
    `wechatId` VARCHAR(50) UNIQUE COMMENT '微信ID',
    -- 用户微信ID，登录使用
    `password` VARCHAR(255) NOT NULL COMMENT '加密后的密码',
    -- 加密后的用户密码，0 游客， 1 普通用户，2管理员
    `permission` ENUM('0', '1', '2') NOT NULL DEFAULT '0' COMMENT '用户权限值，0,1,2',
    `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间'
) ENGINE = InnoDB;

-- 用户基础信息表
CREATE TABLE `userBaseInfo` (
    `uid` BIGINT PRIMARY KEY COMMENT '系统内部用户ID',
    -- 与 userCoreInfo 表关联
    `userName` VARCHAR(100) COMMENT '用户名称',
    -- 用户昵称
    `avatar` VARCHAR(255) COMMENT '用户头像URL',
    -- 用户头像链接
    `gender` ENUM('男', '女') COMMENT '性别',
    -- 用户性别
    `birthDate` DATE COMMENT '生日',
    -- 用户生日
    `bio` VARCHAR(500) COMMENT '用户简介',
    -- 用户简介或个性签名
    FOREIGN KEY (`uid`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 地标表（待完善）
CREATE TABLE `landmark` (
    `landmarkId` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` TEXT,
    `address` VARCHAR(255),
    `city` VARCHAR(100),
    `province` VARCHAR(100),
    `country` VARCHAR(100),
    `latitude` DECIMAL(9, 6),
    -- 纬度
    `longitude` DECIMAL(9, 6),
    -- 经度
    `category` INT
) ENGINE = InnoDB;

-- 用户-好友关系表
CREATE TABLE `userFriend` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '用户ID',
    `userFriendId` BIGINT COMMENT '好友用户ID',
    `createTime` TIMESTAMP COMMENT '创建时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    CONSTRAINT `uuidIdx` UNIQUE (`userId`, `userFriendId`),
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`userFriendId`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 群组表
CREATE TABLE `groups` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `groupId` VARCHAR(9) COMMENT '群组ID',
    `groupName` VARCHAR(16) COMMENT '群组名称',
    `groupHead` VARCHAR(16) COMMENT '群组头像',
    `createTime` TIMESTAMP COMMENT '创建时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    CONSTRAINT `idxGroupId` UNIQUE (`groupId`)
) ENGINE = InnoDB;

-- 用户-群组关系表
CREATE TABLE `userGroup` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '用户ID',
    `groupId` VARCHAR(9) COMMENT '群组ID',
    `createTime` TIMESTAMP COMMENT '创建时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    CONSTRAINT `idxUuid` UNIQUE (`userId`, `groupId`),
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 聊天记录表
CREATE TABLE `chatRecord` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '自己ID',
    `friendId` BIGINT COMMENT '好友ID',
    `msgContent` VARCHAR(512) COMMENT '消息内容',
    `msgDate` TIMESTAMP COMMENT '消息时间',
    `createTime` TIMESTAMP COMMENT '创建时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    `talkType` INT(4),
    `msgType` INT(4) DEFAULT '0',
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`friendId`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 对话框表
CREATE TABLE `talkBox` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '用户ID',
    `talkId` BIGINT COMMENT '对话框ID(好友ID、群组ID)',
    `talkType` INT(4) COMMENT '对话框类型；0好友、1群组',
    `createTime` TIMESTAMP COMMENT '创建时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    CONSTRAINT `idxTalkIdUserId` UNIQUE (`userId`, `talkId`),
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 帖子表
CREATE TABLE `post` (
    `postId` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '帖子ID',
    `landmarkId` BIGINT NOT NULL COMMENT '地标id',
    `uid` BIGINT NOT NULL COMMENT '用户ID',
    `title` VARCHAR(100) NOT NULL COMMENT '帖子标题',
    `content` VARCHAR(5000) NOT NULL COMMENT '帖子内容',
    `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    `tag` INT NULL COMMENT '标签，只选一个,对应0123',
    `visibility` INT NOT NULL COMMENT '对谁可见，0所有人，1朋友可见，2仅自己可见',
    `allowComment` INT NOT NULL COMMENT '是否可评论，0可，1不可',
    `duration` INT NOT NULL COMMENT '0永久，1一年，2一月，3一天，4一小时',
    `contactInfo` VARCHAR(200) NULL COMMENT '联系方式，逗号隔开，依次为手机；qq;微信',
    FOREIGN KEY (`uid`) REFERENCES `userCoreInfo` (`uid`) ON UPDATE CASCADE,
    FOREIGN KEY (`landmarkId`) REFERENCES `landmark`(`landmarkId`) ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 帖子关联资源表
CREATE TABLE `postMedia` (
    `mediaId` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '资源id',
    `postId` BIGINT NOT NULL COMMENT '帖子id',
    `type` INT NOT NULL COMMENT '资源类别，0图片，1音频，2视频',
    `mediaUrl` VARCHAR(300) NOT NULL,
    `createTime` TIMESTAMP NOT NULL,
    FOREIGN KEY (`postId`) REFERENCES `post`(`postId`)
) ENGINE = InnoDB;

-- 帖子点赞
CREATE TABLE `postLike` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `postId` BIGINT NOT NULL,
    `userId` BIGINT NOT NULL,
    FOREIGN KEY (`postId`) REFERENCES `post`(`postId`),
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`)
) ENGINE = InnoDB;

-- 评论表
CREATE TABLE `comment` (
    `commentId` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '评论ID',
    `uid` BIGINT NOT NULL COMMENT '用户ID',
    `postId` BIGINT NOT NULL COMMENT '帖子ID',
    `content` VARCHAR(200) NOT NULL COMMENT '评论内容',
    `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    FOREIGN KEY (`uid`) REFERENCES `userCoreInfo`(`uid`) ON UPDATE CASCADE,
    FOREIGN KEY (`postId`) REFERENCES `post`(`postId`) ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 弹幕类型帖子
CREATE TABLE `bulletScreen` (
    `bulletId` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '弹幕ID',
    `uid` BIGINT NOT NULL COMMENT '用户ID',
    `content` VARCHAR(5000) NOT NULL COMMENT '弹幕内容',
    `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    `visibility` INT NOT NULL COMMENT '对谁可见，0所有人，1朋友可见，2仅自己可见',
    `duration` INT NOT NULL COMMENT '0永久，1一年，2一月，3一天，4一小时',
    `contactInfo` VARCHAR(200) NULL COMMENT '联系方式，逗号隔开，依次为手机；qq;微信',
    FOREIGN KEY (`uid`) REFERENCES `userCoreInfo`(`uid`) ON UPDATE CASCADE
) ENGINE = InnoDB;

-- 弹幕点赞表
CREATE TABLE `bulletScreenLike` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `bulletId` BIGINT NOT NULL,
    `userId` BIGINT NOT NULL,
    FOREIGN KEY (`bulletId`) REFERENCES `bulletScreen`(`bulletId`),
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`)
) ENGINE = InnoDB;
