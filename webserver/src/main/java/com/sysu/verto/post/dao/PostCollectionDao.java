package com.sysu.verto.post.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sysu.verto.post.model.PostCollection;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostCollectionDao extends BaseMapper<PostCollection> {
}