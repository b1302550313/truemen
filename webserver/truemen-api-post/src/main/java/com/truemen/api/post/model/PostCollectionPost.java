package com.truemen.api.post.model;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

//一个关联表模型类，表示Post和PostCollection之间的多对多关系。
@TableName("post_collection_post")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostCollectionPost {
    @TableId
    private Long id; // 关联表的唯一标识符
    private Long collectionId; // 帖子合集的唯一标识符
    private Long postId; // 帖子的唯一标识符
}