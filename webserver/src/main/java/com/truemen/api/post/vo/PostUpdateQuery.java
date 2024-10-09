package com.truemen.api.post.vo;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class PostUpdateQuery {

    @NotEmpty(message = "帖子标题不能为空")
    protected String title;

    @NotEmpty(message = "帖子内容不能为空")
    protected String content;

}
