package com.sysu.verto.post.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("postMedia")
public class PostMedia {
    @TableId(value = "mediaId")
    private Long mediaId;
    private Long postId;
    private Integer type;
    private String mediaUrl;
    private Date createTime;
}