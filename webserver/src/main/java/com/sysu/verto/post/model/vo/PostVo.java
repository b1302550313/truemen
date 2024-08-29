package com.sysu.verto.post.model.vo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@Data
public class PostVo {

    @NotNull(message = "用户ID不能为空")
    protected Long userId;

    @NotNull(message = "帖子类型不能为空")
    public PostType postType;

    @NotEmpty(message = "帖子标题不能为空")
    protected String title;

    @NotEmpty(message = "帖子内容不能为空")
    protected String content;

    protected List<String> mediaUrls;

    protected String location; // 发布位置，可以为空

    public enum PostType {
        Text, Image, Video, Audio
    }

}
