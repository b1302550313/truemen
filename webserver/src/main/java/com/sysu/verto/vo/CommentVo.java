package com.sysu.verto.vo;

import com.baomidou.mybatisplus.annotation.TableId;
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
