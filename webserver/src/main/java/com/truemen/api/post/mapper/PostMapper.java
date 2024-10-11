package com.truemen.api.post.mapper;

import com.truemen.api.post.model.Comment;
import com.truemen.api.post.model.Post;
import com.truemen.api.post.query.CommentUploadQuery;
import com.truemen.api.post.query.PostUploadQuery;
import com.truemen.api.post.vo.CommentVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    /*
    * comment
    * */
    CommentVo commentToCommentVo(Comment comment);
    List<CommentVo> listCommentToCommentVo(List<Comment> comments);
    Comment commentUploadQueryToComment(CommentUploadQuery commentUploadQuery);

    /*
    * post
    * */
    @Mapping(target = "uid", source = "base.uid")
    @Mapping(target = "landmarkId", source = "base.landmarkId")
    @Mapping(target = "title", source = "base.title")
    @Mapping(target = "content", source = "base.content")
    @Mapping(target = "tag", source = "base.tag")
    @Mapping(target = "visibility", source = "base.visibility")
    @Mapping(target = "allowComment", source = "base.allowComment")
    @Mapping(target = "contactInfo", source = "base.contactInfo")
    @Mapping(target = "duration", source = "base.duration")
    @Mapping(target = "createTime", expression = "java(java.time.LocalDateTime.now())") // 设置创建时间为当前时间
    @Mapping(target = "updateTime", expression = "java(java.time.LocalDateTime.now())") // 设置更新时间为当前时间
    Post postUploadQueryToPost(PostUploadQuery postUploadQuery);
}
