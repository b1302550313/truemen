package com.truemen.api.post.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.truemen.api.post.model.PostCollection;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PostCollectionDao extends BaseMapper<PostCollection> {
}