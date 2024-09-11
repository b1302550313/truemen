DROP DATABASE IF EXISTS `verto`;
CREATE DATABASE `verto` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
USE `verto`;

-- 用户核心信息表
CREATE TABLE `userCoreInfo` (
    `uid` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '系统内部用户ID',  -- 系统生成的唯一标识符，不可修改
    `userId` VARCHAR(50) UNIQUE NOT NULL COMMENT '用户可修改的唯一ID',  -- 用户唯一标识，可修改
    `phone` VARCHAR(20) UNIQUE NOT NULL COMMENT '手机号',  -- 用户手机号，登录使用
    `wechatId` VARCHAR(50) UNIQUE COMMENT '微信ID',  -- 用户微信ID，登录使用
    `password` VARCHAR(255) NOT NULL COMMENT '加密后的密码',  -- 加密后的用户密码
    `permission` ENUM('0', '1', '2') NOT NULL DEFAULT '0' COMMENT '用户权限值，0,1,2', -- 0 游客， 1 普通用户，2管理员
    `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '注册时间'  -- 用户注册时间
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 用户基础信息表
CREATE TABLE `userBaseInfo` (
    `uid` BIGINT PRIMARY KEY COMMENT '系统内部用户ID',  -- 与 userCoreInfo 表关联
    `userName` VARCHAR(100) COMMENT '用户名称',  -- 用户昵称
    `avatar` VARCHAR(255) COMMENT '用户头像URL',  -- 用户头像链接
    `gender` ENUM('男', '女') COMMENT '性别',  -- 用户性别
    `birthDate` DATE COMMENT '生日',  -- 用户生日
    `bio` VARCHAR(500) COMMENT '用户简介',  -- 用户简介或个性签名
    FOREIGN KEY (`uid`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- 地标表（待完善）
CREATE TABLE `landmark` (
    `landmarkId` INT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` TEXT,
    `address` VARCHAR(255),
    `city` VARCHAR(100),
    `province` VARCHAR(100),
    `country` VARCHAR(100),
    `latitude` DECIMAL(9, 6),  -- 纬度
    `longitude` DECIMAL(9, 6), -- 经度
    `category` INT
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 群组表
CREATE TABLE `groups` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `groupId` VARCHAR(9) COMMENT '群组ID',
    `groupName` VARCHAR(16) COMMENT '群组名称',
    `groupHead` VARCHAR(16) COMMENT '群组头像',
    `createTime` TIMESTAMP COMMENT '创建时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    CONSTRAINT `idxGroupId` UNIQUE (`groupId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户-群组关系表
CREATE TABLE `userGroup` (
    `id` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '自增ID',
    `userId` BIGINT COMMENT '用户ID',
    `groupId` VARCHAR(9) COMMENT '群组ID',
    `createTime` TIMESTAMP COMMENT '创建时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    CONSTRAINT `idxUuid` UNIQUE (`userId`, `groupId`),
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 帖子表
CREATE TABLE `post` (
    `postId` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '帖子ID',
    `uid` BIGINT NOT NULL COMMENT '用户ID',
    `title` VARCHAR(100) NOT NULL COMMENT '帖子标题',
    `content` VARCHAR(5000) NOT NULL COMMENT '帖子内容',
    `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    `tags` VARCHAR(100) NULL COMMENT '标签，逗号隔开，可多个,对应0123',
    `visibility` INT NOT NULL COMMENT '对谁可见，0所有人，1朋友可见，2仅自己可见',
    `allowComment` INT NOT NULL COMMENT '是否可评论，0可，1不可',
    `duration` INT NOT NULL COMMENT '0永久，1一年，2一月，3一天，4一小时',
    `contactInfo` VARCHAR(200) NULL COMMENT  '联系方式，逗号隔开，依次为手机；qq;微信',
    FOREIGN KEY (`uid`) REFERENCES `userCoreInfo` (`uid`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 帖子关联资源表
CREATE TABLE `postMedia` (
    `mediaId` BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '资源id',
    `postId` BIGINT NOT NULL COMMENT '帖子id',
    `type` INT NOT NULL COMMENT '资源类别，0图片，1音频，2视频',
    `mediaUrl` VARCHAR(300) NOT NULL,
    `createTime` TIMESTAMP NOT NULL,
    FOREIGN KEY (`postId`) REFERENCES `post`(`postId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 帖子点赞
CREATE TABLE `postLike` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `postId` BIGINT NOT NULL,
    `userId` BIGINT NOT NULL,
    FOREIGN KEY (`postId`) REFERENCES `post`(`postId`),
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 评论表
CREATE TABLE `comment` (
    `commentId` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '评论ID',
    `uid` BIGINT NOT NULL COMMENT '用户ID',
    `postId` BIGINT NOT NULL COMMENT '帖子ID',
    `content` VARCHAR(200) NOT NULL COMMENT '评论内容',
    `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    FOREIGN KEY (`uid`) REFERENCES `userCoreInfo`(`uid`) ON UPDATE CASCADE,
    FOREIGN KEY (`postId`) REFERENCES `post`(`postId`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 弹幕类型帖子
CREATE TABLE `bulletScreen` (
    `bulletId` BIGINT PRIMARY KEY AUTO_INCREMENT COMMENT '弹幕ID',
    `uid` BIGINT NOT NULL COMMENT '用户ID',
    `content` VARCHAR(5000) NOT NULL COMMENT '弹幕内容',
    `createTime` TIMESTAMP DEFAULT CURRENT_TIMESTAMP COMMENT '发布时间',
    `updateTime` TIMESTAMP COMMENT '更新时间',
    `visibility` INT NOT NULL COMMENT '对谁可见，0所有人，1朋友可见，2仅自己可见',
    `duration` INT NOT NULL COMMENT '0永久，1一年，2一月，3一天，4一小时',
    `contactInfo` VARCHAR(200) NULL COMMENT  '联系方式，逗号隔开，依次为手机；qq;微信',
    FOREIGN KEY (`uid`) REFERENCES `userCoreInfo`(`uid`) ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 弹幕点赞表
CREATE TABLE `bulletScreenLike` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `bulletId` BIGINT NOT NULL,
    `userId` BIGINT NOT NULL,
    FOREIGN KEY (`bulletId`) REFERENCES `bulletScreen`(`bulletId`),
    FOREIGN KEY (`userId`) REFERENCES `userCoreInfo`(`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 插入用户核心信息
INSERT INTO `userCoreInfo` (`userId`, `phone`, `wechatId`, `password`, `permission`) VALUES 
('user123', '12345678901', 'wx_user123', 'encrypted_password_1', '1'),
('user456', '09876543211', 'wx_user456', 'encrypted_password_2', '2'),
('user789', '11223344551', NULL, 'encrypted_password_3', '1');

-- 插入用户基础信息
INSERT INTO `userBaseInfo` (`uid`, `userName`, `avatar`, `gender`, `birthDate`, `bio`) VALUES 
(1, 'Alice', 'avatar_01.png', '女', '1990-01-01', 'Hello, I am Alice.'),
(2, 'Bob', 'avatar_02.png', '男', '1985-05-15', 'Hi there, I am Bob.'),
(3, 'Charlie', 'avatar_03.png', '男', '1992-08-20', 'Charlie here.');

-- 插入地标信息
INSERT INTO `landmark` (`name`, `description`, `address`, `city`, `province`, `country`, `latitude`, `longitude`, `category`) VALUES 
('Landmark A', 'A famous landmark.', '123 Main St', 'CityA', 'ProvinceA', 'CountryA', 34.052235, -118.243683, 1),
('Landmark B', 'Another famous landmark.', '456 Maple Rd', 'CityB', 'ProvinceB', 'CountryB', 40.712776, -74.005974, 2);

-- 插入好友关系数据
INSERT INTO `userFriend` (`userId`, `userFriendId`, `createTime`, `updateTime`) VALUES 
(1, 2, '2024-02-23 13:02:45', '2024-02-23 13:02:45'),
(1, 3, '2024-02-23 13:02:45', '2024-02-23 13:02:45'),
(2, 3, '2024-02-23 13:02:45', '2024-02-23 13:02:45');

-- 插入群组数据
INSERT INTO `groups` (`groupId`, `groupName`, `groupHead`, `createTime`, `updateTime`) VALUES 
('5307397', '楚们交流群', 'group_1', '2024-01-01 00:00:00', '2024-01-01 00:00:00');

-- 插入用户-群组关系数据
INSERT INTO `userGroup` (`userId`, `groupId`, `createTime`, `updateTime`) VALUES 
(1, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(2, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00'),
(3, '5307397', '2024-01-01 00:00:00', '2024-01-01 00:00:00');

-- 插入聊天记录数据
INSERT INTO `chatRecord` (`userId`, `friendId`, `msgContent`, `msgDate`, `createTime`, `updateTime`, `talkType`, `msgType`) VALUES 
(2, 1, 'Test2to1', '2024-02-23 11:33:37', '2024-02-23 11:33:37', '2024-02-23 11:33:37', 0, 0),
(3, 1, 'Test3to1', '2024-02-23 11:33:49', '2024-02-23 11:33:49', '2024-02-23 11:33:49', 0, 0),
(2, 3, 'Test2to3', '2024-02-23 11:33:58', '2024-02-23 11:33:58', '2024-02-23 11:33:58', 0, 0);

-- 插入对话框数据
INSERT INTO `talkBox` (`userId`, `talkId`, `talkType`, `createTime`, `updateTime`) VALUES 
(1, 1, 1, '2024-02-23 19:35:54', '2024-02-23 19:35:54'),
(1, 2, 0, '2024-02-23 19:36:03', '2024-02-23 19:36:03'),
(3, 1, 1, '2024-02-23 15:26:32', '2024-02-23 15:26:32');

-- 插入帖子数据
INSERT INTO `post` (`uid`, `title`, `content`, `createTime`, `updateTime`, `tags`, `visibility`, `allowComment`, `duration`, `contactInfo`) VALUES
(1, '探索宇宙的奥秘', '宇宙是如此浩瀚，充满了无数的星系和未知的秘密。', NOW(), NULL, '0,1', 0, 0, 0, '13800138000;42;wechat_1'),
(2, '健康生活每一天', '保持健康的生活方式对于提高生活质量至关重要。', NOW(), NULL, '1', 0, 1, 1, '13800138001;3311;wechat_2'),
(1, '编程的乐趣', '编程不仅仅是一门技术，更是一种艺术和乐趣。', NOW(), NULL, '2,3', 2, 0, 2, '13800138002;123;wechat_3'),
(2, '旅行的意义', '旅行不仅仅是为了看风景，更是为了体验不同的文化和生活。', NOW(), NULL, '1,3', 1, 1, 3, '13800138003;123;wechat_4'),
(3, '音乐的力量', '音乐有着无法言喻的力量，能够触动人心，激发情感。', NOW(), NULL, '1,2,3', 0, 0, 4, '13800138004;123123;wechat_5'),
(1, '摄影的艺术', '摄影是一种捕捉瞬间，记录生活的艺术形式。', NOW(), NULL, '0,1,2,3', 2, 1, 0, '13800138005;4424;wechat_6'),
(3, '阅读的乐趣', '阅读能够开阔视野，丰富内心世界。', NOW(), NULL, '2,3', 1, 0, 1, '13800138006;123123;wechat_7'),
(3, '科技改变生活', '科技的发展正在不断地改变着我们的生活方式。', NOW(), NULL, '0', 2, 1, 2, '13800138007;123123;wechat_8'),
(2, '环保的重要性', '保护环境是每个人的责任，也是为了我们共同的未来。', NOW(), NULL, '0', 1, 0, 3, '13800138008;123123;wechat_9'),
(1, '运动的好处', '定期运动有助于保持身体健康，提高生活质量。', NOW(), NULL, '1,3', 1, 1, 4, '13800138009;123123;wechat_10');

-- 插入媒体资源数据
INSERT INTO `postMedia` (`postId`, `type`, `mediaUrl`, `createTime`) VALUES
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

-- 插入帖子点赞
INSERT INTO `postLike` (`postId`, `userId`) VALUES
(1, 2),
(1, 3),
(2, 1),
(2, 1),
(3, 2),
(3, 2),
(4, 3),
(4, 3),
(5, 1),
(5, 2);

-- 插入帖子评论
INSERT INTO `comment` (`uid`, `postId`, `content`) VALUES
(1, 1, '非常有趣的帖子，学习到了很多！'),
(2, 1, '同意楼上，内容很有启发性。'),
(3, 2, '这个观点我不太同意，但我尊重你的看法。'),
(1, 2, '感谢分享，期待更多类似的讨论。'),
(1, 3, '很棒的见解，我也有类似的想法。'),
(3, 3, '这个分析很到位，赞一个！'),
(2, 4, '旅行总是让人充满期待，期待你的下一篇帖子。'),
(3, 4, '我也喜欢旅行，可以交流一下经验。'),
(1, 5, '音乐是生活中不可或缺的部分，感谢分享。'),
(2, 5, '这首歌我也很喜欢，有类似的推荐吗？');

-- 插入弹幕帖子
INSERT INTO `bulletScreen` (`uid`, `content`, `visibility`, `duration`, `contactInfo`) VALUES
(1, '这是一条有趣的弹幕！', 0,  0, '13800138000;123456789;wechat_1'),
(2, '大家晚上好，这里是我的弹幕。', 2,  1, '13800138001;123456790;wechat_2'),
(3, '欢迎来到直播间，一起嗨起来！', 0,  2, '13800138002;123456791;wechat_3'),
(1, '今天的直播太精彩了，不容错过！', 1,  3, '13800138003;123456792;wechat_4'),
(3, '喜欢这个视频的请点赞哦！', 2,  4, '13800138004;123456793;wechat_5'),
(2, '求背景音乐的名字！', 0, 0, '13800138005;123456794;wechat_6'),
(1, '这个技巧太实用了，谢谢分享！', 1,  1, '13800138006;123456795;wechat_7'),
(1, '大家周末有什么计划吗？', 2,  2, '13800138007;123456796;wechat_8'),
(3, '直播结束，感谢大家的陪伴！', 0,  3, '13800138008;123456797;wechat_9'),
(1, '下次直播见，不见不散！', 1,  4, '13800138009;123456798;wechat_10');