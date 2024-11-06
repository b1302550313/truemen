package com.truemen.api.landmark.controller;

import com.truemen.api.landmark.model.Landmark;
import com.truemen.api.landmark.service.LandmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 地标操作控制器，提供地标详情、收藏、点赞、评分等功能
 */
@RestController
@RequestMapping("/api/v1/landmarks")
public class LandmarkController {

    @Autowired
    private LandmarkService landmarkService;

    /**
     * 根据地标ID获取地标详细信息
     *
     * @param landmarkId 地标ID
     * @return 地标详细信息，若未找到则返回404
     */
    @GetMapping("/{landmarkId}")
    public ResponseEntity<Landmark> getLandmarkById(@PathVariable Long landmarkId) {
        Landmark landmark = landmarkService.getLandmarkById(landmarkId);
        if (landmark != null) {
            return ResponseEntity.ok(landmark);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * 切换用户对地标的收藏状态
     *
     * @param landmarkId 地标ID
     * @param userId     用户ID
     * @return 收藏操作结果
     */
    @PostMapping("/{landmarkId}/favorite")
    public ResponseEntity<String> toggleFavorite(@PathVariable Long landmarkId, @RequestParam Long userId) {
        boolean isFavorite = landmarkService.toggleFavorite(landmarkId, userId);
        String message = isFavorite ? "已添加到收藏" : "已从收藏中移除";
        return ResponseEntity.ok(message);
    }

    /**
     * 用户对地标进行点赞或取消点赞
     *
     * @param landmarkId 地标ID
     * @param userId     用户ID
     * @param isLike     是否点赞
     * @return 空响应体
     */
    @PostMapping("/{landmarkId}/like")
    public ResponseEntity<Void> likeLandmark(@PathVariable Long landmarkId, @RequestParam Long userId,
                                             @RequestParam boolean isLike) {
        landmarkService.updateLikes(landmarkId, userId, isLike);
        return ResponseEntity.ok().build();
    }

    /**
     * 用户对地标进行评分
     *
     * @param landmarkId 地标ID
     * @param userId     用户ID
     * @param rating     评分，范围1-5
     * @return 空响应体
     */
    @PostMapping("/{landmarkId}/rate")
    public ResponseEntity<String> rateLandmark(@PathVariable Long landmarkId, @RequestParam Long userId,
                                               @RequestParam int rating) {
        if (rating < 1 || rating > 5) {
            return ResponseEntity.badRequest().body("评分必须在1到5之间");
        }
        landmarkService.updateRating(landmarkId, userId, rating);
        return ResponseEntity.ok("评分成功");
    }
}
