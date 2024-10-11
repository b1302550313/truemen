package com.truemen.api.post.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("bulletScreen")
public class BulletScreen {
    @TableId(value = "bulletId")
    private Long bulletId;
    protected Long landmarkId;
    protected Long uid;
    protected String content;
    protected LocalDateTime createTime;
    protected LocalDateTime updateTime;
    protected Long tag;
    protected Long visibility;
    protected Long allowComment;
    protected Long duration;
    protected String contactInfo;
}