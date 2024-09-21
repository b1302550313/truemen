package com.truemen.api.post.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.truemen.api.post.model.Comment;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CommentDao extends BaseMapper<Comment> {
}
