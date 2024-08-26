package com.sysu.verto.service;

import com.sysu.verto.dao.PostDao;
import com.sysu.verto.model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {
    @Autowired
    private PostDao postDao;

    public List<Post> getAllPosts() {
        return postDao.findAllPosts();
    }
}