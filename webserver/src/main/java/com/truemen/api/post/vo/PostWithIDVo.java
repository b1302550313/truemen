package com.truemen.api.post.vo;

import com.truemen.api.post.query.PostUploadQuery;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PostWithIDVo extends PostUploadQuery {
    @NotNull(message = "帖子ID不能为空")
    protected Long postId;
}
