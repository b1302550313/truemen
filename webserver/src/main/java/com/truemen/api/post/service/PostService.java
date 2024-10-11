package com.truemen.api.post.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.truemen.api.common.debug.Debug;
import com.truemen.api.common.exception.ErrorCode;
import com.truemen.api.common.exception.ServerException;
import com.truemen.api.post.dao.PostDao;
import com.truemen.api.post.mapper.PostMapper;
import com.truemen.api.post.model.Post;
import com.truemen.api.post.query.PostUpdateQuery;
import com.truemen.api.post.query.PostUploadQuery;
import com.truemen.api.post.vo.PostWithIDVo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.truemen.api.post.dao.PostCollectionPostDao;

@Slf4j
@Service
public class PostService extends ServiceImpl<PostDao, Post> {

    @Autowired
    private PostCollectionPostDao postCollectionPostDao;

    public PostWithIDVo getPost(Integer pid) {

        Post post = baseMapper.selectById(pid.longValue());
        if (post == null)
            throw new ServerException(ErrorCode.INTERNAL_SERVER_ERROR);

        PostWithIDVo postWithIDVo = new PostWithIDVo();
        // postWithIDVo.setPostId(pid.longValue());
        // postWithIDVo.setPostType(PostVo.PostType.valueOf(post.getType().name()));
        // postWithIDVo.setTitle(post.getTitle());
        // postWithIDVo.setContent(post.getContent());
        // postWithIDVo.setLocation(post.getLocation());
        // postWithIDVo.setUserId(post.getUid());
        // postWithIDVo.setMediaUrls(ConVertUrlList.convertStringToUrlList(post.getMediaUrls()));
        return postWithIDVo;
    }

    public Long upLoadPost(PostUploadQuery postUploadQuery) {
        Post post = PostMapper.INSTANCE.postUploadQueryToPost(postUploadQuery);
//        Debug.printFields(post);

        int affectLine = baseMapper.insert(post);
        if (affectLine!=1){
            return null;
        }else {
            return post.getPostId();
        }

        //todo
        //这里添加合集考虑前端调用添加合集api
        // 添加帖子到合集
//        if (postVo.getCollectionId() != null) {
//            PostCollectionPost collectionPost = PostCollectionPost.builder()
//                    .collectionId(postVo.getCollectionId())
//                    .postId(post.getPostId())
//                    .build();
//            postCollectionPostDao.insert(collectionPost);
//        }
//        return (long) baseMapper.insert(post);
    }

    public boolean deletePost(Integer pid) {
        int rows = baseMapper.deleteById(pid);
        if (rows == 0) {
            return false;
        }
        return true;
    }

    public boolean updatePost(Integer pid, PostUpdateQuery query) {
        LambdaQueryWrapper<Post> qw = new LambdaQueryWrapper();
        qw.eq(Post::getPostId, pid);
        Post post = new Post();
        post.setContent(query.getContent());
        post.setTitle(query.getTitle());
        int rows = baseMapper.update(post, qw);
        if (rows == 0)
            return false;
        else
            return true;
    }

    public Map<String, Long> getPostCountByLocation() {
        List<Post> posts = list();
        // return posts.stream()
        // .filter(post -> post.getLocation() != null)
        // .collect(Collectors.groupingBy(Post::getLocation, Collectors.counting()));
        return null;
    }
}