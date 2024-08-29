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

@RestController
@RequestMapping(value = "/post")
@Validated
public class PostController {
  private static final Logger log = LoggerFactory.getLogger(PostController.class);
  @Autowired
  private PostService postService;

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

}

/*
 * 
 * 评论管理接口:
 * - 接口5: POST /api/v1/posts/{postId}/comments
 * - 描述: 用户对帖子进行评论。
 * - 请求参数:
 * - postId - 帖子ID
 * - userId - 评论者ID
 * - comment - 评论内容
 * - 响应数据: 新创建评论的ID
 * - 接口6: GET /api/v1/posts/{postId}/comments
 * - 描述: 获取指定帖子的评论列表。
 * - 请求参数:
 * - postId - 帖子ID
 * - page - 页码（用于分页）
 * - pageSize - 每页评论数
 * - 响应数据: 评论列表（数组）
 * - 接口7: POST /api/v1/comments/{commentId}/replies
 * - 描述: 用户对某条评论进行回复。
 * - 请求参数:
 * - commentId - 评论ID
 * - userId - 回复者ID
 * - reply - 回复内容
 * - 响应数据: 新创建回复的ID
 * - 接口8: GET /api/v1/comments/{commentId}/replies
 * - 描述: 获取某条评论的回复列表。
 * - 请求参数:
 * - commentId - 评论ID
 * - page - 页码（用于分页）
 * - pageSize - 每页回复数
 * - 响应数据: 回复列表（数组）
 * 点赞管理接口:
 */
