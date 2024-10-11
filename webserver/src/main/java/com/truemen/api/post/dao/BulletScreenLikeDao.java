package com.truemen.api.post.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.truemen.api.post.model.BulletScreenLike;
import com.truemen.api.post.query.PostUploadQuery;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface BulletScreenLikeDao extends BaseMapper<BulletScreenLike> {
}
