package com.truemen.api.post.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.truemen.api.common.result.PageResult;
import com.truemen.api.post.query.BasePageQuery;
import com.truemen.api.post.query.CommentUploadQuery;
import com.truemen.api.post.service.CommentService;
import com.truemen.api.post.service.MediaService;
import com.truemen.api.post.vo.CommentVo;
import com.truemen.api.post.vo.PostDetailVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;
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
import com.truemen.api.post.query.PostUpdateQuery;
import com.truemen.api.post.query.PostUploadQuery;
import com.truemen.api.post.service.PostService;

import jakarta.validation.Valid;

@Slf4j
@RestController
@RequestMapping(value = "/api/v1/post")
@Validated
public class PostController {
  // transaction
  @Autowired
  private PlatformTransactionManager transactionManager;

  @Autowired
  private PostService postService;
  @Autowired
  private CommentService commentService;
  @Autowired
  private MediaService mediaService;


  /*
   * - 描述: 用户创建新帖子。帖子可以包含文本、图片、音频等内容。
   */
  @PostMapping("/upload")
  public Result<Map<String,Long>> uploadPost(
          @Valid @RequestBody PostUploadQuery postUploadQuery) {
    // Debug.printFields(postUploadQuery);
    TransactionStatus status = transactionManager.getTransaction(new DefaultTransactionDefinition());

    // upload post
    Long postId = postService.upLoadPost(postUploadQuery);
    Map<String, Long> data = new HashMap<>();
    data.put("postId",postId);
    if (postId ==null){
      transactionManager.rollback(status);
      throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    // form media record
    boolean success = mediaService.addMediaToPost(postId,postUploadQuery.getMedia());
    if (!success){
      transactionManager.rollback(status);
      throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

    transactionManager.commit(status);
    return Result.ok(data);

  }

  /*
   * - 描述: 获取指定帖子的详细信息。
   * - 响应数据: 帖子详细信息
   */
  @GetMapping("/detail/{postId}")
  public Result<PostDetailVo> getPostDetail(@PathVariable("postId") Long postId) {
    // base info
    PostDetailVo postDetailVo = postService.getPostDetail(postId);
    // media info
    List<Long> mediaId = mediaService.getMediaInfoOfPost(postId);
    postDetailVo.setMediaId(mediaId);
    return Result.ok(postDetailVo);
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
   * - 描述: 获取不同地点的帖子数。
   * - 响应数据: 不同地点的帖子数
   */
//  @GetMapping("/location/count")
//  public Result<Map<String, Long>> getPostCountByLocation() {
//    Map<String, Long> postCountByLocation = postService.getPostCountByLocation();
//    return Result.ok(postCountByLocation);
//  }



  @GetMapping("/comment/list/{postId}")
  public Result<PageResult<CommentVo>> getCommentByPostID(
          @PathVariable("postId") String postID,
          @RequestBody BasePageQuery query
          ){
    PageResult<CommentVo> data= commentService.listCommentByPostID(Integer.parseInt(postID),query);
    return Result.ok(data);
  }

  @PostMapping("/comment/upload")
  public Result<Map<String,Long>> uploadComment(
          @Valid @RequestBody CommentUploadQuery commentUploadQuery){

    Long commentID = commentService.upLoadComment(commentUploadQuery);
    // error
    if (commentID == null) {
      throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
    }
    Map<String,Long> data = new HashMap<>();
    data.put("commentId",commentID);
    return Result.ok(data);
  }
}