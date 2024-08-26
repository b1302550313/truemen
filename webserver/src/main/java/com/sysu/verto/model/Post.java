package com.sysu.verto.model;

import lombok.Data;

import java.util.Date;

@Data
public class Post {
    private Long postId;
    private Long uid;
    private String title;
    private PostType type;
    private String content;
    private Date createTime;

    public enum PostType {
        Text, Image, Video, Audio
    }
}

