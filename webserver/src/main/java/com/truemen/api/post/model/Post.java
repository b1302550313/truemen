package com.truemen.api.post.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@TableName("post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @TableId
    protected Long postId;
    protected Long landmarkId;
    protected Long uid;
    protected String title;
    protected String content;
    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;
    protected Long tag;
    protected Long visibility;
    protected Long allowComment;
    protected Long duration;
    protected String contactInfo;
}
