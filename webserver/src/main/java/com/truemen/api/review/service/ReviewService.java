package com.truemen.api.review.service;

import com.truemen.api.review.model.Review;
import com.truemen.api.review.repository.ReviewRepository; // 假设你有这个 Repository
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByLandmarkId(Long landmarkId) {
        return reviewRepository.findByLandmarkId(landmarkId);
    }

    public Review addReview(Long landmarkId, Review review) {
        review.setLandmarkId(landmarkId); // 确保评论与地标相关联
        return reviewRepository.save(review);
    }

    public boolean deleteReview(Long reviewId) {
        Optional<Review> review = reviewRepository.findById(reviewId);
        if (review.isPresent()) {
            reviewRepository.delete(review.get());
            return true;
        }
        return false;
    }
}
