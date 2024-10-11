package com.truemen.api.post.mapper;

import com.truemen.api.post.model.BulletScreen;
import com.truemen.api.post.query.BulletScreenUploadQuery;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BulletScreenMapper {
    BulletScreenMapper INSTANCE = Mappers.getMapper(BulletScreenMapper.class);

    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())") // 设置创建时间为当前时间
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())") // 设置更新时间为当前时间
    BulletScreen uploadBulletScreenToBulletScreen(BulletScreenUploadQuery bulletScreenUploadQuery);
}
