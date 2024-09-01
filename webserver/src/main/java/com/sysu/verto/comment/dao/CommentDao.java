package com.sysu.verto.comment.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sysu.verto.comment.model.Comment;
import com.sysu.verto.post.model.Post;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao extends BaseMapper<Comment> {
}