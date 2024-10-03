package com.truemen.api.landmark.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

/**
 * 用户与地标交互实体类
 * 用于记录用户对地标的收藏、点赞和评分等交互行为
 */
@Entity
@Table(name = "user_landmark_interaction")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserLandmarkInteraction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // 主键ID

    @Column(name = "user_id")
    private Long userId; // 用户ID

    @Column(name = "landmark_id")
    private Long landmarkId; // 地标ID

    @Column(name = "is_favorite")
    private Boolean isFavorite; // 是否收藏

    @Column(name = "like_status")
    private Integer likeStatus; // 点赞状态（0：未操作，1：点赞，-1：踩）

    @Column(name = "rating")
    private Integer rating; // 用户评分

    /**
     * 构造函数
     * 
     * @param userId     用户ID
     * @param landmarkId 地标ID
     */
    public UserLandmarkInteraction(Long userId, Long landmarkId) {
        this.userId = userId;
        this.landmarkId = landmarkId;
        this.isFavorite = false; // 默认未收藏
        this.likeStatus = 0; // 默认未操作
        this.rating = null; // 默认未评分
    }
}
