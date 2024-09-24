package com.truemen.api.post.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.truemen.api.post.dao.CommentDao;
import com.truemen.api.post.model.Comment;
import com.truemen.api.post.model.vo.CommentListPostQuery;
import com.truemen.api.post.model.vo.CommentUploadQuery;
import com.truemen.api.post.model.vo.CommentVo;
import com.truemen.api.common.result.PageResult;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class CommentService extends ServiceImpl<CommentDao, Comment> {

    public PageResult<CommentVo> listPostComment(Integer postId,
            CommentListPostQuery query) {
        LambdaQueryWrapper<Comment> qw = new LambdaQueryWrapper<>();
        qw.eq(Comment::getPostId, postId);
        Page page = new Page(query.getPage(), query.getLimit());
        IPage pageResult = baseMapper.selectPage(page, qw);

        System.out.println("总记录数：" + pageResult.getTotal());
        System.out.println("当前页码：" + pageResult.getCurrent());
        System.out.println("每页记录数：" + pageResult.getSize());
        System.out.println("当前页数据：" + pageResult.getRecords());

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
