package com.sysu.verto.comment.model.vo;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentUploadQuery {

    @NotNull(message = "用户ID不能为空")
    protected Long uid;

    @NotNull(message = "帖子ID不能为空")
    protected Long postId;

    @NotEmpty(message = "帖子内容不能为空")
    protected String content;

}
