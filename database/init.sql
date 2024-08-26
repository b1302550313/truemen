drop database if exists `verto`;

create database `verto` character set utf8mb4 collate utf8mb4_general_ci;

use `verto`;

-- 用户UID、手机号、用户权限、注册时间等核心信息
create table `user_core_info` (
    `uid` bigint primary key auto_increment comment '用户ID',
    `phone` varchar(20) not null comment '手机号',
    `permission` bigint not null default 0 comment '用户权限值，每一比特位表示一种权限，0表示无权限，1表示有权限',
    `create_time` datetime default current_timestamp comment '注册时间'
);

-- 用户基本信息表
create table `user_base_info` (
    `uid` bigint primary key auto_increment comment '用户ID',
    `user_name` varchar(20) comment '用户名称',
    `avatar` varchar(255) comment '用户头像URL',
    `gender` enum ('男', '女') comment '性别',
    foreign key (uid) references user_core_info (uid) on delete cascade on update cascade
);

-- 好友关系表
create table `friendship` (
    `id` bigint primary key auto_increment comment '好友关系ID',
    `uid` bigint not null comment '用户ID',
    `friend_id` bigint comment '好友ID',
    foreign key (uid) references user_core_info (uid) on delete cascade on update cascade
);

-- 帖子表
create table `post` (
    `post_id` bigint primary key auto_increment comment '帖子ID',
    `uid` bigint not null comment '用户ID',
    `title` varchar(100) not null comment '帖子标题',
    `type` enum('Text', 'Image', 'Video', 'Audio') not null comment '帖子类型',
    `content` varchar(5000) not null comment '帖子内容',
    `create_time` datetime default current_timestamp comment '发布时间',
    foreign key (uid) references user_core_info (uid) on update cascade
);

-- 评论表

create table `comment` (
    `comment_id` bigint primary key auto_increment comment '评论ID',
    `uid` bigint not null comment '用户ID',
    `post_id` bigint not null comment '帖子ID',
    `content` varchar(200) not null comment '评论内容',
    `create_time` datetime default current_timestamp comment '发布时间',
    foreign key (uid) references user_core_info (uid) on update cascade,
    foreign key (post_id) references post (post_id) on update cascade
);



