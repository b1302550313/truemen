package com.sysu.verto.dao;


import com.sysu.verto.model.Post;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface PostDao {
    @Select("select * from post")
    List<Post> findAllPosts();

}
