package com.truemen.api.post.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.truemen.api.common.util.FileExtValid;
import com.truemen.api.post.dao.PostMediaDao;
import com.truemen.api.post.model.Post;
import com.truemen.api.post.model.PostMedia;
import com.truemen.api.post.query.PostUploadQuery;
import org.springframework.stereotype.Service;

import javax.print.attribute.standard.Media;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MediaService extends ServiceImpl<PostMediaDao, PostMedia> {

    public boolean addMediaToPost(Long postId, List<String> mediaResource){

        Collection<PostMedia> media = mediaResource.stream()
                .map(meidaPath->{
                    FileExtValid.FileType fileType = FileExtValid.classifyFile(meidaPath);
                    Long fileExt;
                    switch (fileType){
                        case AUDIO: fileExt=0L;break;
                        case PHOTO: fileExt=1L;break;
                        case VIDEO: fileExt=2L;break;
                        default:fileExt=null;break;
                    }
                    PostMedia temp = PostMedia.builder()
                            .mediaUrl(meidaPath)
                            .type(fileExt)
                            .postId(postId)
                            .createTime(LocalDateTime.now())
                            .build();
                    return temp;
                }).collect(Collectors.toSet());

        return saveBatch(media);
    }

    public List<Long> getMediaInfoOfPost(Long postId){
        LambdaQueryWrapper<PostMedia> qw = new LambdaQueryWrapper<>();
        qw.eq(PostMedia::getPostId,postId);
        return list(qw).stream()
                .map(postMedia -> {
                    return postMedia.getMediaId();
                })
                .collect(Collectors.toList());
    }


}
