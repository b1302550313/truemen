package com.sysu.verto.vo;

import jakarta.validation.constraints.NotNull;

public class PostWithIDVo extends PostVo{
    @NotNull(message = "帖子ID不能为空")
    private Long postId;
}
