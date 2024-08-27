package com.sysu.verto.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sysu.verto.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostDao extends BaseMapper<Post> {
//    @Select("select * from post")
//    List<Post> findAllPosts();

}
