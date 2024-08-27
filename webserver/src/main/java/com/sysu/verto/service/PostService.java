package com.sysu.verto.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sysu.verto.dao.PostDao;
import com.sysu.verto.framework.util.ConVertUrlListToString;
import com.sysu.verto.model.Post;
import com.sysu.verto.vo.PostVo;
import com.sysu.verto.vo.PostWithIDVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class PostService extends ServiceImpl<PostDao, Post>{
    @Autowired
    private PostDao postDao;

    public Post getPost(Integer pid) {
        return postDao.selectById(pid);
    }

    public Integer upLoadPost(PostVo postVo){
        Post post = Post.builder()
                .uid(postVo.getUserId())
                .title(postVo.getTitle())
                .content(postVo.getContent())
                .type(Post.PostType.valueOf(postVo.postType.name()))
                .createTime(new Date())
                .mediaUrls(ConVertUrlListToString.convertMediaUrlsToString(postVo.getMediaUrls()))
                .build();
        return baseMapper.insert(post);
    }
}