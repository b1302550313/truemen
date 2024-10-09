package com.truemen.api.post.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.truemen.api.post.dao.CommentDao;
import com.truemen.api.post.model.Comment;
import com.truemen.api.post.query.BasePageQuery;
import com.truemen.api.post.vo.CommentUploadQuery;
import com.truemen.api.post.vo.CommentVo;
import com.truemen.api.common.result.PageResult;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class CommentService extends ServiceImpl<CommentDao, Comment> {

    public PageResult<CommentVo> listCommentByPostID(Integer postId,
                                                     BasePageQuery query) {
        LambdaQueryWrapper<Comment> qw = new LambdaQueryWrapper<>();
        qw.eq(Comment::getPostId, postId);
        Page page = new Page(query.getPage(), query.getLimit());
        IPage pageResult = baseMapper.selectPage(page, qw);

        this.log.info("总记录数：{}", pageResult.getTotal());
        this.log.info("当前页码：{}", pageResult.getCurrent());
        this.log.info("每页记录数：{}", pageResult.getSize());
        this.log.info("当前页数据：{}", pageResult.getRecords());

        return new PageResult<CommentVo>(pageResult.getRecords(), pageResult.getTotal());
    }

    public Integer upLoadComment(CommentUploadQuery commentUploadQuery) {
        Comment comment = Comment.builder()
                .uid(commentUploadQuery.getUid())
                .content(commentUploadQuery.getContent())
                .postId(commentUploadQuery.getPostId())
                .build();
        return baseMapper.insert(comment);
    }

}
