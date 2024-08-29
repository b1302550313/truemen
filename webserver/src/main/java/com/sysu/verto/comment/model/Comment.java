package com.sysu.verto.comment.model;

import com.baomidou.mybatisplus.annotation.TableId;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class Comment {
    @TableId
    protected Long uid;

    protected Long commentId;

    protected Long postId;

    protected String content;

    protected Date create_time;
}
