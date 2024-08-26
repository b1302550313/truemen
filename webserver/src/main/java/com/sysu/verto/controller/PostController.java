package com.sysu.verto.controller;

import com.sysu.verto.framework.result.Result;
import com.sysu.verto.model.Post;
import com.sysu.verto.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/post")
public class PostController {

    private PostService postService;

    /*
    - 描述: 用户创建新帖子。帖子可以包含文本、图片、音频等内容。
    - 请求参数:
      - userId - 发布者ID
      - postType - 帖子类型（如求助帖、留言帖、图文帖、音频帖）
      - content - 帖子内容（文本）
      - mediaUrls - 媒体文件的URL列表（可选，适用于图片、音频等）
      - location - 发布位置（可选，适用于定位帖）
    - 响应数据: 新创建帖子的ID
     */
    @PostMapping("/upload")
    public Result<Integer> uploadPost(){
        return Result.ok();
    }

    @GetMapping("/all")
    public Result<List<Post>> getAllPosts() {
        List<Post> posts = postService.getAllPosts();
        return Result.ok(posts);
    }
}
