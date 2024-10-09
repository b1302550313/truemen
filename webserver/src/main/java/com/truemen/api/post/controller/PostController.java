package com.truemen.api.post.controller;

import java.util.Map;

import com.truemen.api.common.result.PageResult;
import com.truemen.api.post.query.BasePageQuery;
import com.truemen.api.post.service.CommentService;
import com.truemen.api.post.vo.CommentVo;
import jakarta.persistence.criteria.CriteriaBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.truemen.api.common.exception.ErrorCode;
import com.truemen.api.common.exception.ServerException;
import com.truemen.api.common.result.Result;
import com.truemen.api.post.model.PostCollection;
import com.truemen.api.post.vo.PostUpdateQuery;
import com.truemen.api.post.vo.PostVo;
import com.truemen.api.post.vo.PostWithIDVo;
import com.truemen.api.post.service.PostCollectionService;
import com.truemen.api.post.service.PostService;

import jakarta.validation.Valid;

@RestController
@RequestMapping(value = "/api/v1/post")
@Validated
public class PostController {
  @Autowired
  private PostService postService;
  @Autowired
  private CommentService commentService;
  @Autowired
  private PostCollectionService postCollectionService;

  /*
   * - 描述: 用户创建新帖子。帖子可以包含文本、图片、音频等内容。
   * - 响应数据: 新创建帖子的ID
   */
  @PostMapping("/upload/normal")
  public Result<Integer> uploadPost(@Valid @RequestBody PostVo postVo) {
    System.out.println("/upload/normal");
    Integer pid = postService.upLoadPost(postVo);
    if (pid != null) {
      return Result.ok(pid);
    } else {
      throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  /*
   * - 描述: 获取指定帖子的详细信息。
   * - 响应数据: 帖子详细信息
   */
  @GetMapping("/detail/normal/{postId}")
  public Result<PostWithIDVo> getPostDetail(@PathVariable Integer postId) {
    PostWithIDVo postWithIDVo = postService.getPost(postId);
    return Result.ok(postWithIDVo);
  }

  /*
   * - 描述: 更新已有帖子的内容。用户可以修改文本内容，或添加/删除媒体文件
   * - 响应数据: 操作结果（成功或失败）
   */
  @PutMapping("/detail/normal/{postId}")
  public Result updatePostDetail(@PathVariable Integer postId, @Valid @RequestBody PostUpdateQuery query) {
    boolean res = postService.updatePost(postId, query);
    if (res == false)
      throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    return Result.ok();
  }

  /*
   * - 描述: 删除指定帖子。
   * - 响应数据: 操作结果（成功或失败）
   */
  @DeleteMapping("/delete/normal/{postId}")
  public Result deletePost(@PathVariable Integer postId) {
    boolean reult = postService.deletePost(postId);
    if (reult) {
      return Result.ok();
    } else {
      return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

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
   * - 描述: 获取不同地点的帖子数。
   * - 响应数据: 不同地点的帖子数
   */
  @GetMapping("/location/count")
  public Result<Map<String, Long>> getPostCountByLocation() {
    Map<String, Long> postCountByLocation = postService.getPostCountByLocation();
    return Result.ok(postCountByLocation);
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

  @GetMapping("/comment/list/{postId}")
  public @Valid PageResult<CommentVo> getCommentByPostID(
          @PathVariable("postId") String postID,
          @RequestBody BasePageQuery query
          ){
    PageResult<CommentVo> result= commentService.listCommentByPostID(Integer.parseInt(postID),query);
    return result;
  }
}