package com.truemen.api.post.mapper;

import com.truemen.api.post.model.Comment;
import com.truemen.api.post.query.CommentUploadQuery;
import com.truemen.api.post.vo.CommentVo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PostMapper {
    PostMapper INSTANCE = Mappers.getMapper(PostMapper.class);

    CommentVo commentToCommentVo(Comment comment);
    List<CommentVo> listCommentToCommentVo(List<Comment> comments);

    Comment commentUploadQueryToComment(CommentUploadQuery commentUploadQuery);
}
