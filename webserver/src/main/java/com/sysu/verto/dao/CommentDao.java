package com.sysu.verto.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sysu.verto.model.Comment;
import com.sysu.verto.model.Post;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao extends BaseMapper<Comment> {
}
