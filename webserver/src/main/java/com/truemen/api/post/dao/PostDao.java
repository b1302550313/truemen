package com.truemen.api.post.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.truemen.api.post.model.Post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostDao extends BaseMapper<Post> {
}
