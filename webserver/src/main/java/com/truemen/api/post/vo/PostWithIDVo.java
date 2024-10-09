package com.truemen.api.post.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostWithIDVo extends PostVo {
    @NotNull(message = "帖子ID不能为空")
    protected Long postId;
}
