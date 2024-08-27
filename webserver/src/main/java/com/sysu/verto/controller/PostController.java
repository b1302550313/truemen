package com.sysu.verto.controller;

import com.sysu.verto.framework.exception.ErrorCode;
import com.sysu.verto.framework.exception.ServerException;
import com.sysu.verto.framework.result.Result;
import com.sysu.verto.model.Post;
import com.sysu.verto.service.PostService;
import com.sysu.verto.vo.PostVo;
import com.sysu.verto.vo.PostWithIDVo;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.ibatis.jdbc.Null;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
@Validated
public class PostController {
    @Autowired
    private PostService postService;

    /*
    - 描述: 用户创建新帖子。帖子可以包含文本、图片、音频等内容。
    - 响应数据: 新创建帖子的ID
     */
    @PostMapping("/upload")
    public Result<Integer> uploadPost(@Valid @RequestBody PostVo postVo){
        Integer pid = postService.upLoadPost(postVo);
        if (pid!=null){
            return Result.ok(pid);
        }else{
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
    }

    /*
    接口2: GET /api/v1/posts/{postId}
    - 描述: 获取指定帖子的详细信息，包括内容、发布者信息、点赞数、评论列表等。
    - 响应数据: 帖子详细信息
    * */
    @GetMapping("/detail/{pid}")
    public Result<PostWithIDVo> getAllPosts(@RequestParam Integer pid) {
        Post post = postService.getPost(pid);
        if (post!=null){

        }else{
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return null;
    }
}




/*

接口3: PUT /api/v1/posts/{postId}
- 描述: 更新已有帖子的内容。用户可以修改文本内容，或添加/删除媒体文件。
- 请求参数:
  - postId - 帖子ID
  - content - 更新后的帖子内容（文本）
  - mediaUrls - 更新后的媒体文件URL列表（可选）
- 响应数据: 操作结果（成功或失败）
接口4: DELETE /api/v1/posts/{postId}
- 描述: 删除指定帖子。
- 请求参数:
  - postId - 帖子ID
- 响应数据: 操作结果（成功或失败）

评论管理接口:
- 接口5: POST /api/v1/posts/{postId}/comments
  - 描述: 用户对帖子进行评论。
  - 请求参数:
    - postId - 帖子ID
    - userId - 评论者ID
    - comment - 评论内容
  - 响应数据: 新创建评论的ID
- 接口6: GET /api/v1/posts/{postId}/comments
  - 描述: 获取指定帖子的评论列表。
  - 请求参数:
    - postId - 帖子ID
    - page - 页码（用于分页）
    - pageSize - 每页评论数
  - 响应数据: 评论列表（数组）
- 接口7: POST /api/v1/comments/{commentId}/replies
  - 描述: 用户对某条评论进行回复。
  - 请求参数:
    - commentId - 评论ID
    - userId - 回复者ID
    - reply - 回复内容
  - 响应数据: 新创建回复的ID
- 接口8: GET /api/v1/comments/{commentId}/replies
  - 描述: 获取某条评论的回复列表。
  - 请求参数:
    - commentId - 评论ID
    - page - 页码（用于分页）
    - pageSize - 每页回复数
  - 响应数据: 回复列表（数组）
点赞管理接口:
* */
