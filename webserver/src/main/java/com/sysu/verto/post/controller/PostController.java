package com.sysu.verto.post.controller;

import com.sysu.verto.common.exception.ErrorCode;
import com.sysu.verto.common.exception.ServerException;
import com.sysu.verto.common.result.Result;
import com.sysu.verto.post.model.Post;
import com.sysu.verto.post.model.vo.PostUpdateQuery;
import com.sysu.verto.post.model.vo.PostVo;
import com.sysu.verto.post.model.vo.PostWithIDVo;
import com.sysu.verto.post.service.PostService;

import jakarta.servlet.http.PushBuilder;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.jdbc.Null;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import com.sysu.verto.post.model.PostCollection;
import com.sysu.verto.post.service.PostCollectionService;
import jakarta.validation.Valid;
import java.util.Map;


@RestController
@RequestMapping(value = "/post")
@Validated
public class PostController {
  private static final Logger log = LoggerFactory.getLogger(PostController.class);
  @Autowired
  private PostService postService;

  @Autowired
  private PostCollectionService postCollectionService;
  /*
   * - 描述: 用户创建新帖子。帖子可以包含文本、图片、音频等内容。
   * - 响应数据: 新创建帖子的ID
   */
  @PostMapping("/upload")
  public Result<Integer> uploadPost(@Valid @RequestBody PostVo postVo) {
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
  @GetMapping("/detail/{postId}")
  public Result<PostWithIDVo> getPostDetail(@PathVariable Integer postId) {
    PostWithIDVo postWithIDVo = postService.getPost(postId);
    return Result.ok(postWithIDVo);
  }

  /*
   * - 描述: 更新已有帖子的内容。用户可以修改文本内容，或添加/删除媒体文件
   * - 响应数据: 操作结果（成功或失败）
   */
  @PutMapping("/detail/{postId}")
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
  @DeleteMapping("/{postId}")
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
  @DeleteMapping("/collection/{collectionId}")
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
  @DeleteMapping("/collection/{collectionId}/post/{postId}")
  public Result removePostFromCollection(@PathVariable Long collectionId, @PathVariable Long postId) {
      boolean result = postCollectionService.removePostFromCollection(collectionId, postId);
      if (result) {
          return Result.ok();
      } else {
          return Result.error(ErrorCode.INTERNAL_SERVER_ERROR);
      }
  }
}