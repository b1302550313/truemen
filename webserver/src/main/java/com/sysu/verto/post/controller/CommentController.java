package com.sysu.verto.post.controller;

import com.sysu.verto.post.model.vo.CommentListPostQuery;
import com.sysu.verto.post.model.vo.CommentUploadQuery;
import com.sysu.verto.post.model.vo.CommentVo;
import com.sysu.verto.post.service.CommentService;
import com.sysu.verto.common.exception.ErrorCode;
import com.sysu.verto.common.exception.ServerException;
import com.sysu.verto.common.result.PageResult;
import com.sysu.verto.common.result.Result;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/comment")
@Validated
public class CommentController {

  @Autowired
  private CommentService commentService;

  /*
   * - 描述: 用户对帖子进行评论。
   * - 响应数据: 新创建评论的ID
   */
  @PostMapping("/upload/normal")
  public Result<Integer> uploadComment(@Valid @RequestBody CommentUploadQuery commentUploadQuery) {
    Integer pid = commentService.upLoadComment(commentUploadQuery);
    if (pid != null) {
      return Result.ok(pid);
    } else {
      throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
  }

  /*
   * - 描述: 获取指定帖子的评论列表。
   * - 响应数据: 评论列表（数组）
   */
  @GetMapping("/list/post/{postId}")
  public Result<PageResult<CommentVo>> listPostComment(@PathVariable Integer postId,
      @RequestBody @Valid CommentListPostQuery query) {
    PageResult<CommentVo> res = commentService.listPostComment(postId, query);
    return Result.ok(res);
  }

}