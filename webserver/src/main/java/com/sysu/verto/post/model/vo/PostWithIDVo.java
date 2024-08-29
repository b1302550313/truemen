package com.sysu.verto.post.model.vo;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostWithIDVo extends PostVo {
    @NotNull(message = "帖子ID不能为空")
    protected Long postId;
}
