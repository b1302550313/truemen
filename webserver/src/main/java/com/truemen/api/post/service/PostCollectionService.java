package com.truemen.api.post.service;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.truemen.api.post.dao.PostCollectionDao;
import com.truemen.api.post.model.PostCollection;
import com.truemen.api.post.model.PostCollectionPost;
import com.truemen.api.post.dao.PostCollectionPostDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PostCollectionService extends ServiceImpl<PostCollectionDao, PostCollection> {

    @Autowired
    private PostCollectionPostDao postCollectionPostDao;

    public boolean createCollection(PostCollection collection) {
        return save(collection);
    }

    public boolean updateCollection(PostCollection collection) {
        return updateById(collection);
    }

    public boolean deleteCollection(Long collectionId) {
        return removeById(collectionId);
    }

    public PostCollection getCollection(Long collectionId) {
        return getById(collectionId);
    }

    public boolean addPostToCollection(Long collectionId, Long postId) {
        PostCollectionPost collectionPost = PostCollectionPost.builder()
                .collectionId(collectionId)
                .postId(postId)
                .build();
        return postCollectionPostDao.insert(collectionPost) > 0;
    }

    public boolean removePostFromCollection(Long collectionId, Long postId) {
        return postCollectionPostDao.deleteByCollectionIdAndPostId(collectionId, postId) > 0;
    }
}