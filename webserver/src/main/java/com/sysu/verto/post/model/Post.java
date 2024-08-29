package com.sysu.verto.post.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@TableName("post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @TableId
    private Long postId;
    private Long uid;
    private String title;
    private PostType type;
    private String content;
    private Date createTime;
    private String mediaUrls;
    private String location;

    public enum PostType {
        Text, Image, Video, Audio
    }
}
