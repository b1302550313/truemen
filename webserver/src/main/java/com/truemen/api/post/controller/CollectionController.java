package com.truemen.api.post.controller;

import com.truemen.api.common.exception.ErrorCode;
import com.truemen.api.common.result.Result;
import com.truemen.api.post.model.PostCollection;
import com.truemen.api.post.service.PostCollectionService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/post")
@Validated
public class CollectionController {

    @Autowired
    private PostCollectionService postCollectionService;


    /*
     * - 描述: 创建帖子合集。
     * - 响应数据: 操作结果（成功或失败）
     */
    @PostMapping("/collection")
    public Result createCollection(@Valid @RequestBody PostCollection collection) {
        boolean result = postCollectionService.createCollection(collection);
        if (result) {
            return Result.ok();
        } else {
            return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * - 描述: 更新帖子合集。
     * - 响应数据: 操作结果（成功或失败）
     */
    @PutMapping("/collection/{collectionId}")
    public Result updateCollection(@PathVariable Long collectionId, @Valid @RequestBody PostCollection collection) {
        collection.setCollectionId(collectionId);
        boolean result = postCollectionService.updateCollection(collection);
        if (result) {
            return Result.ok();
        } else {
            return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * - 描述: 删除帖子合集。
     * - 响应数据: 操作结果（成功或失败）
     */
    @DeleteMapping("/collection/delete/{collectionId}")
    public Result deleteCollection(@PathVariable Long collectionId) {
        boolean result = postCollectionService.deleteCollection(collectionId);
        if (result) {
            return Result.ok();
        } else {
            return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * - 描述: 获取指定帖子合集的详细信息。
     * - 响应数据: 帖子合集详细信息
     */
    @GetMapping("/collection/{collectionId}")
    public Result<PostCollection> getCollectionDetail(@PathVariable Long collectionId) {
        PostCollection collection = postCollectionService.getCollection(collectionId);
        return Result.ok(collection);
    }



    /*
     * - 描述: 添加帖子到合集。
     * - 响应数据: 操作结果（成功或失败）
     */
    @PostMapping("/collection/{collectionId}/post/{postId}")
    public Result addPostToCollection(@PathVariable Long collectionId, @PathVariable Long postId) {
        boolean result = postCollectionService.addPostToCollection(collectionId, postId);
        if (result) {
            return Result.ok();
        } else {
            return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    /*
     * - 描述: 从合集中移除帖子。
     * - 响应数据: 操作结果（成功或失败）
     */
    @DeleteMapping("/collection/{collectionId}/delete/{postId}")
    public Result removePostFromCollection(@PathVariable Long collectionId, @PathVariable Long postId) {
        boolean result = postCollectionService.removePostFromCollection(collectionId, postId);
        if (result) {
            return Result.ok();
        } else {
            return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }
}
