package com.sysu.verto.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@TableName("post")
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

