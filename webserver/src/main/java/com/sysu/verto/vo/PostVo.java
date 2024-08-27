package com.sysu.verto.vo;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Null;
import lombok.Data;

import java.util.List;

@Data
public class PostVo {

    @NotNull(message = "用户ID不能为空")
    private Long userId;

    @NotNull(message = "帖子类型不能为空")
    public PostType postType;

    @NotEmpty(message = "帖子标题不能为空")
    private String title;

    @NotEmpty(message = "帖子内容不能为空")
    private String content;

    private List<String> mediaUrls;

    private String location; // 发布位置，可以为空

    public enum PostType {
        Text, Image, Video, Audio
    }

}
