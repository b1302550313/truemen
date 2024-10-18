package com.truemen.api.post.query;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

@NoArgsConstructor
@Data
public class PostUpdateQuery {

    @NotEmpty(message = "帖子标题不能为空")
    protected String title;

    @NotEmpty(message = "帖子内容不能为空")
    protected String content;

    @Range(max = 1L)
    @NotNull(message = "allowComment不能为空")
    private Long allowComment;

    @NotEmpty(message = "contactInfo不能为空")
    private String contactInfo;

    @Range(max = 4L)
    @NotNull(message = "duration不能为空")
    private Long duration;

    @Range(max = 3L)
    @NotNull(message = "tag不能为空")
    private Long tag;

    @Range(max = 2L)
    @NotNull(message = "visibility不能为空")
    private Long visibility;

}
