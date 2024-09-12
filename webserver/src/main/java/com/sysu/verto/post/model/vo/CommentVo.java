package com.sysu.verto.post.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CommentVo {
    @NotNull
    protected Long uid;
    @NotNull
    protected Long commentId;
    @NotNull
    protected Long postId;
    @NotNull
    protected String content;

    protected Date create_time;
}
