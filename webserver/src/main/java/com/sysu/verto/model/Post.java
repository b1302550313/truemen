package com.sysu.verto.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
import java.util.List;

@TableName("post")
@Data
@Builder
public class Post {
    private Long postId;
    private Long uid;
    private String title;
    private PostType type;
    private String content;
    private Date createTime;
    private String mediaUrls;

    public enum PostType {
        Text, Image, Video, Audio
    }
}

