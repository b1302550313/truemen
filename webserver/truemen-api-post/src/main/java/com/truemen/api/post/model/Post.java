package com.truemen.api.post.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

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
    private String content;
    private Date createTime;
    private Date updateTime;
    private Long tag;
    private Long visibility;
    private Long allowComment;
    private Long duration;
    private String contactInfo;
}
