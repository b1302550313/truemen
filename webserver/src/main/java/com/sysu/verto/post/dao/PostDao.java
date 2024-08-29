package com.sysu.verto.post.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sysu.verto.post.model.Post;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostDao extends BaseMapper<Post> {
}
