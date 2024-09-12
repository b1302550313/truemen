package com.sysu.verto.post.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sysu.verto.comment.model.Comment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao extends BaseMapper<Comment> {
}
