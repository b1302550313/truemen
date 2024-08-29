DROP DATABASE IF EXISTS `verto`;
CREATE DATABASE `verto` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `verto`;

-- 用户核心信息表
CREATE TABLE `user_core_info` (
    `uid` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '用户ID',
    `phone` VARCHAR(20) NOT NULL COMMENT '手机号',
    `permission` BIGINT NOT NULL DEFAULT 0 COMMENT '用户权限值，每一比特位表示一种权限，0表示无权限，1表示有权限',
    `create_time` DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间'
);

-- 用户基础信息表
CREATE TABLE `user_base_info` (
    `uid` BIGINT PRIMARY KEY COMMENT '用户ID',
    `user_name` VARCHAR(100) COMMENT '用户名称',
    `avatar` VARCHAR(255) COMMENT '用户头像URL',
    `gender` ENUM('男', '女') COMMENT '性别',
    FOREIGN KEY (`uid`) REFERENCES `user_core_info`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- 好友关系表
CREATE TABLE `user_friend` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '用户ID',
    `userFriendId` BIGINT COMMENT '好友用户ID',
    `createTime` DATETIME COMMENT '创建时间',
    `updateTime` DATETIME COMMENT '更新时间',
    CONSTRAINT `uuid_idx` UNIQUE (`userId`, `userFriendId`),
    FOREIGN KEY (`userId`) REFERENCES `user_core_info`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`userFriendId`) REFERENCES `user_core_info`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 群组表
CREATE TABLE `groups` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `groupId` VARCHAR(9) COMMENT '群组ID',
    `groupName` VARCHAR(16) COMMENT '群组名称',
    `groupHead` VARCHAR(16) COMMENT '群组头像',
    `createTime` DATETIME COMMENT '创建时间',
    `updateTime` DATETIME COMMENT '更新时间',
    CONSTRAINT `idx_groupId` UNIQUE (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户-群组关系表
CREATE TABLE `user_group` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '用户ID',
    `groupId` VARCHAR(9) COMMENT '群组ID',
    `createTime` DATETIME COMMENT '创建时间',
    `updateTime` DATETIME COMMENT '更新时间',
    CONSTRAINT `idx_uuid` UNIQUE (`userId`, `groupId`),
    FOREIGN KEY (`userId`) REFERENCES `user_core_info`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 聊天记录表
CREATE TABLE `chat_record` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '自己ID',
    `friendId` BIGINT COMMENT '好友ID',
    `msgContent` VARCHAR(512) COMMENT '消息内容',
    `msgDate` DATETIME COMMENT '消息时间',
    `createTime` DATETIME COMMENT '创建时间',
    `updateTime` DATETIME COMMENT '更新时间',
    `talkType` INT(4),
    `msgType` INT(4) DEFAULT '0',
    FOREIGN KEY (`userId`) REFERENCES `user_core_info`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`friendId`) REFERENCES `user_core_info`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 对话框表
CREATE TABLE `talk_box` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '用户ID',
    `talkId` VARCHAR(9) COMMENT '对话框ID(好友ID、群组ID)',
    `talkType` INT(4) COMMENT '对话框类型；0好友、1群组',
    `createTime` DATETIME COMMENT '创建时间',
    `updateTime` DATETIME COMMENT '更新时间',
    CONSTRAINT `idx_talkId_userId` UNIQUE (`userId`, `talkId`),
    FOREIGN KEY (`userId`) REFERENCES `user_core_info`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入用户核心信息
INSERT INTO `user_core_info` (`phone`, `permission`) VALUES 
('1234567890', 1), 
('0987654321', 2), 
('796542178', 1), 
('523088136', 1), 
('123456001', 1), 
('123456002', 1), 
('123456003', 1), 
('123456004', 1), 
('123456005', 1), 
('123456006', 1), 
('123456007', 2), 
('123456008', 1);

-- 插入用户基础信息
INSERT INTO `user_base_info` (`uid`, `user_name`, `avatar`, `gender`) VALUES 
(1, 'aaaa', '01_50', '男'), 
(2, 'bbbb', '02_50', '男'), 
(3, '汤姆', '03_50', '女'), 
(4, '杰瑞', '04_50', '女'), 
(5, '比丘卡', '05_50', '男'), 
(6, '兰兰', '06_50', '女'), 
(7, 'Alex', '07_50', '女'), 
(8, '小白', '08_50', '男'), 
(9, '铃铛', '09_50', '女'), 
(10, '嘻嘻', '10_50', '男'), 
(11, 'sss', '11_50', '男'), 
(12, '哈哈哈', '12_50', '女');

-- 插入好友关系数据
INSERT INTO `user_friend` (id, userId, userFriendId, createTime, updateTime) VALUES 
(27, 4, 1, '2024-02-23 13:02:45', '2024-02-23 13:02:45'),
(28, 1, 4, '2024-02-23 13:02:45', '2024-02-23 13:02:45'),
(29, 1, 11, '2024-02-23 13:02:56', '2024-02-23 13:02:56'),
(30, 11, 1, '2024-02-23 13:02:56', '2024-02-23 13:02:56');

-- 插入群组数据
INSERT INTO `groups` (id, groupId, groupName, groupHead, createTime, updateTime) VALUES 
(1, '5307397', '中山大学交流群', 'group_1', '2024-01-01 00:00:00', '2024-01-01 00:00:00');

-- 插入用户-群组关系数据
INSERT INTO `user_group` (id, userId, groupId, createTime, updateTime) VALUES 
(1, 1, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(11, 2, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(12, 3, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(13, 4, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(14, 5, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(15, 6, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(16, 7, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(17, 8, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(18, 9, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(19, 10, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(20, 11, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(21, 12, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00');

-- 插入聊天记录数据
INSERT INTO `chat_record` (id, userId, friendId, msgContent, msgDate, createTime, updateTime, talkType, msgType) VALUES 
(3, 4, 1, '中午好哦。周末你在家干啥呢', '2024-02-23 11:33:37', '2024-02-23 11:33:37', '2024-02-23 11:33:37', 0, 0),
(4, 4, 1, '怎么不说话？', '2024-02-23 11:33:49', '2024-02-23 11:33:49', '2024-02-23 11:33:49', 0, 0),
(5, 1, 4, '做饭呢，做饭呢。', '2024-02-23 11:33:58', '2024-02-23 11:33:58', '2024-02-23 11:33:58', 0, 0),
(6, 1, 4, '可香了，要不要来吃饭。吃完在学习。', '2024-02-23 11:34:24', '2024-02-23 11:34:24', '2024-02-23 11:34:24', 0, 0),
(7, 4, 1, '咿呀！', '2024-02-23 11:34:35', '2024-02-23 11:34:35', '2024-02-23 11:34:35', 0, 0),
(8, 4, 1, '走了走了！', '2024-02-23 11:34:38', '2024-02-23 11:34:38', '2024-02-23 11:34:38', 0, 0),
(9, 1, 4, '!!咋了就知道吃，不学习呀！', '2024-02-23 11:34:56', '2024-02-23 11:34:56', '2024-02-23 11:34:56', 0, 0),
(10, 4, 1, '嗨，小伙伴们，你们都在干啥呢？', '2024-02-23 15:26:32', '2024-02-23 15:26:32', '2024-02-23 15:26:32', 1, 0),
(11, 1, 4, '我在家呢，今天周末了。自己做点好吃的。', '2024-02-23 15:26:49', '2024-02-23 15:26:49', '2024-02-23 15:26:49', 1, 0),
(12, 3, 4, '哈哈哈，我也在。我想吃肉', '2024-02-23 15:27:00', '2024-02-23 15:27:00', '2024-02-23 15:27:00', 1, 0),
(13, 4, 3, '你太胖了，你吃点罗布吧', '2024-02-23 15:27:10', '2024-02-23 15:27:10', '2024-02-23 15:27:10', 1, 0),
(14, 4, 3, '萝卜', '2024-02-23 15:27:15', '2024-02-23 15:27:15', '2024-02-23 15:27:15', 1, 0),
(15, 1, 4, 'hehehe', '2024-02-23 18:54:08', '2024-02-23 18:54:08', '2024-02-23 18:54:08', 1, 0),
(16, 1, 4, '我要测试断线重连了', '2024-02-23 18:54:17', '2024-02-23 18:54:17', '2024-02-23 18:54:17', 1, 0),
(17, 1, 4, '主动断开服务端，等待恢复', '2024-02-23 18:54:24', '2024-02-23 18:54:24', '2024-02-23 18:54:24', 1, 0),
(18, 4, 1, '纳尼！？好的！', '2024-02-23 18:54:30', '2024-02-23 18:54:30', '2024-02-23 18:54:30', 1, 0),
(19, 1, 4, '恢复了，可以通信了', '2024-02-23 18:55:16', '2024-02-23 18:55:16', '2024-02-23 18:55:16', 1, 0),
(20, 4, 1, '哇！', '2024-02-23 18:55:19', '2024-02-23 18:55:19', '2024-02-23 18:55:19', 1, 0),
(21, 4, 1, '666', '2024-02-23 18:55:21', '2024-02-23 18:55:21', '2024-02-23 18:55:21', 1, 0),
(37, 1, 4, '我们可以发送表情了哦', '2024-02-23 14:17:41', '2024-02-23 14:17:41', '2024-02-23 14:17:41', 0, 0),
(38, 1, 4, 'f_22', '2024-02-23 14:17:45', '2024-02-23 14:17:45', '2024-02-23 14:17:45', 0, 1),
(39, 4, 1, '哇，真的哦！太好了！', '2024-02-23 14:17:59', '2024-02-23 14:17:59', '2024-02-23 14:17:59', 0, 0),
(40, 4, 1, 'f_24', '2024-02-23 14:18:03', '2024-02-23 14:18:03', '2024-02-23 14:18:03', 0, 1),
(41, 4, 1, 'f_05', '2024-02-23 14:18:09', '2024-02-23 14:18:09', '2024-02-23 14:18:09', 0, 1),
(42, 4, 1, 'f_15', '2024-02-23 14:18:12', '2024-02-23 14:18:12', '2024-02-23 14:18:12', 0, 1),
(43, 4, 1, 'f_12', '2024-02-23 14:18:14', '2024-02-23 14:18:14', '2024-02-23 14:18:14', 0, 1),
(44, 4, 1, 'f_25', '2024-02-23 14:18:17', '2024-02-23 14:18:17', '2024-02-23 14:18:17', 0, 1),
(45, 1, 4, '叮咚咚', '2024-02-23 14:20:51', '2024-02-23 14:20:51', '2024-02-23 14:20:51', 1, 0),
(46, 4, 1, '的', '2024-02-23 14:21:39', '2024-02-23 14:21:39', '2024-02-23 14:21:39', 0, 0),
(47, 1, 4, '哈哈哈', '2024-02-23 14:38:02', '2024-02-23 14:38:02', '2024-02-23 14:38:02', 0, 0),
(48, 1, 4, '在吗', '2024-02-23 14:38:06', '2024-02-23 14:38:06', '2024-02-23 14:38:06', 0, 0),
(49, 4, 1, '有人吗', '2024-02-23 14:38:11', '2024-02-23 14:38:12', '2024-02-23 14:38:12', 1, 0),
(50, 1, 4, '有人有人', '2024-02-23 14:38:19', '2024-02-23 14:38:19', '2024-02-23 14:38:19', 1, 0),
(51, 4, 1, '这回就对了', '2024-02-23 14:38:29', '2024-02-23 14:38:29', '2024-02-23 14:38:29', 0, 0);

-- 插入对话框数据
INSERT INTO `talk_box` (id, userId, talkId, talkType, createTime, updateTime) VALUES 
(1, 1, '5307397', 1, '2024-02-23 19:35:54', '2024-02-23 19:35:54'),
(2, 4, '5307397', 1, '2024-02-23 19:36:00', '2024-02-23 19:36:00'),
(3, 1, '4', 0, '2024-02-23 19:36:03', '2024-02-23 19:36:03'),
(4, 4, '1', 0, '2024-02-23 19:36:03', '2024-02-23 19:36:03'),
(5, 3, '5307397', 1, '2024-02-23 15:26:32', '2024-02-23 15:26:32');
