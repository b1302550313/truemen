package com.truemen.api.post.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.truemen.api.post.dao.CommentDao;
import com.truemen.api.post.mapper.PostMapper;
import com.truemen.api.post.model.Comment;
import com.truemen.api.post.query.BasePageQuery;
import com.truemen.api.post.query.CommentUploadQuery;
import com.truemen.api.post.vo.CommentVo;
import com.truemen.api.common.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentService extends ServiceImpl<CommentDao, Comment> {

    public PageResult<CommentVo> listCommentByPostID(Integer postId,
                                                     BasePageQuery query) {
        LambdaQueryWrapper<Comment> qw = new LambdaQueryWrapper<>();
        qw.eq(Comment::getPostId, postId);
        Page<Comment> page = new Page<Comment>(query.getPage(), query.getLimit());
        IPage<Comment> pageResult = baseMapper.selectPage(page, qw);

        log.info("总记录数：{}", pageResult.getTotal());
        log.info("当前页码：{}", pageResult.getCurrent());
        log.info("每页记录数：{}", pageResult.getSize());
        log.info("当前页数据：{}", pageResult.getRecords());
        List<CommentVo> records = PostMapper.INSTANCE.listCommentToCommentVo(pageResult.getRecords());
        return new PageResult<CommentVo>(records, pageResult.getTotal());
    }

    public Long upLoadComment(CommentUploadQuery commentUploadQuery) {
        Comment comment = PostMapper.INSTANCE.commentUploadQueryToComment(commentUploadQuery);
        boolean result = save(comment);
        if (result)return comment.getCommentId();
        else return null;
    }

}
