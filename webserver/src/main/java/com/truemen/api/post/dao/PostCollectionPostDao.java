package com.truemen.api.post.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.truemen.api.post.model.PostCollectionPost;

@Mapper
public interface PostCollectionPostDao extends BaseMapper<PostCollectionPost> {
    int deleteByCollectionIdAndPostId(@Param("collectionId") Long collectionId, @Param("postId") Long postId);
}