package com.sysu.verto.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sysu.verto.dao.PostDao;
import com.sysu.verto.framework.exception.ErrorCode;
import com.sysu.verto.framework.exception.ServerException;
import com.sysu.verto.framework.util.ConVertUrlList;
import com.sysu.verto.model.Post;
import com.sysu.verto.vo.PostUpdateQuery;
import com.sysu.verto.vo.PostVo;
import com.sysu.verto.vo.PostWithIDVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PostService extends ServiceImpl<PostDao, Post>{

    public PostWithIDVo getPost(Integer pid) {

        Post post = baseMapper.selectById(pid.longValue());
        if(post==null) throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);

        PostWithIDVo postWithIDVo = new PostWithIDVo();
        postWithIDVo.setPostId(pid.longValue());
        postWithIDVo.setPostType(PostVo.PostType.valueOf(post.getType().name()));
        postWithIDVo.setTitle(post.getTitle());
        postWithIDVo.setContent(post.getContent());
        postWithIDVo.setLocation(post.getLocation());
        postWithIDVo.setUserId(post.getUid());
        postWithIDVo.setMediaUrls(ConVertUrlList.convertStringToUrlList(post.getMediaUrls()));
        return postWithIDVo;
    }

    public Integer upLoadPost(PostVo postVo){
        Post post = Post.builder()
                .uid(postVo.getUserId())
                .title(postVo.getTitle())
                .content(postVo.getContent())
                .type(Post.PostType.valueOf(postVo.postType.name()))
                .createTime(new Date())
                .mediaUrls(ConVertUrlList.convertMediaUrlsToString(postVo.getMediaUrls()))
                .location(postVo.getLocation())
                .build();
        return baseMapper.insert(post);
    }

    public boolean deletePost(Integer pid){
        int rows = baseMapper.deleteById(pid);
        if (rows==0){
            return false;
        }
        return true;
    }

    public boolean updatePost(Integer pid, PostUpdateQuery query){
        LambdaQueryWrapper<Post> qw = new LambdaQueryWrapper();
        qw.eq(Post::getPostId, pid);
        Post post = new Post();
        post.setContent(query.getContent());
        post.setTitle(query.getTitle());
        int rows = baseMapper.update(post,qw);
        if (rows==0)return false;
        else return true;
    }
}