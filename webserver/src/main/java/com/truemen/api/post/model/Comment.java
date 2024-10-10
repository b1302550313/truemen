package com.truemen.api.post.model;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
/**
 * Comment
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@TableName("comment")
public class Comment {
    /**
     * 评论ID
     */
    @TableId(value = "commentId")
    private Long commentId;
    /**
     * 评论内容
     */
    private String content;
    /**
     * 发布时间
     */
    private Date createTime;
    /**
     * 帖子ID
     */
    private Long postId;
    /**
     * 用户ID
     */
    private Long uid;
}