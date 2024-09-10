package com.sysu.verto.post.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@TableName("post_collection")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCollection {
    @TableId
    private Long collectionId; // 帖子合集的唯一标识符
    private Long userId; // 创建该帖子合集的用户ID
    private String name; // 帖子合集的名称
    private String coverUrl; // 帖子合集封面的URL
}