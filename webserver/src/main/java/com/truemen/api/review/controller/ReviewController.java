package com.truemen.api.review.controller;

import com.truemen.api.review.model.Review; // 修改为你实际的包路径
import com.truemen.api.review.service.ReviewService; // 修改为你实际的包路径
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/landmarks/{landmarkId}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    /**
     * 获取指定地标的所有评论
     *
     * @param landmarkId 地标ID
     * @return 所有评论列表
     */
    @GetMapping
    public ResponseEntity<List<Review>> getReviewsByLandmarkId(@PathVariable("landmarkId") Long landmarkId) {
        List<Review> reviews = reviewService.getReviewsByLandmarkId(landmarkId);
        if (reviews.isEmpty()) {
            return ResponseEntity.noContent().build(); // 如果没有评论，返回204
        }
        return ResponseEntity.ok(reviews);
    }

    /**
     * 添加评论
     *
     * @param landmarkId 地标ID
     * @param review     评论内容
     * @return 新增的评论
     */
    @PostMapping
    public ResponseEntity<Review> addReview(@PathVariable("landmarkId") Long landmarkId, @RequestBody Review review) {
        Review createdReview = reviewService.addReview(landmarkId, review);
        return ResponseEntity.status(201).body(createdReview); // 返回201 Created和新增的评论
    }

    /**
     * 删除评论
     *
     * @param reviewId 评论ID
     * @return 响应状态
     */
    @DeleteMapping("/{reviewId}")
    public ResponseEntity<Void> deleteReview(@PathVariable("reviewId") Long reviewId) {
        boolean isDeleted = reviewService.deleteReview(reviewId);
        if (isDeleted) {
            return ResponseEntity.noContent().build(); // 删除成功，返回204 No Content
        } else {
            return ResponseEntity.notFound().build(); // 评论未找到，返回404 Not Found
        }
    }
}
