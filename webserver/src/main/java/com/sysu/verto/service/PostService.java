package com.sysu.verto.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sysu.verto.dao.PostDao;
import com.sysu.verto.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService extends ServiceImpl<PostDao, Post>{
    @Autowired
    private PostDao postDao;

    public List<Post> getAllPosts() {
        QueryWrapper qw = new QueryWrapper();
        return postDao.selectList(qw);
    }
}