package com.truemen.api.post.controller;

import com.example.model.PostMediaFile;
import com.example.service.PostMediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/posts/{postId}/media")
public class PostMediaController {

    @Autowired
    private PostMediaService postMediaService;

    // 上传媒体文件（图片或音频）
    @PostMapping
    public PostMediaFile uploadMedia(@PathVariable("postId") int postId, @RequestParam("file") MultipartFile file, @RequestParam("mediaType") String mediaType) {
        return postMediaService.uploadMedia(postId, file, mediaType);
    }

    // 获取帖子媒体
    @GetMapping
    public List<PostMediaFile> getMediaByPostId(@PathVariable("postId") int postId) {
        return postMediaService.getMediaByPostId(postId);
    }
}
