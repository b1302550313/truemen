package com.truemen.api.landmark.dao;

import com.truemen.api.landmark.model.UserLandmarkInteraction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * 用户地标交互数据访问接口
 * 提供对 UserLandmarkInteraction 实体的数据库操作方法
 */
@Repository
public interface UserLandmarkInteractionRepository extends JpaRepository<UserLandmarkInteraction, Long> {

    /**
     * 根据用户ID和地标ID查找用户与地标的交互记录
     * 
     * @param userId     用户ID
     * @param landmarkId 地标ID
     * @return 可能存在的用户地标交互记录
     */
    Optional<UserLandmarkInteraction> findByUserIdAndLandmarkId(Long userId, Long landmarkId);

    /**
     * 获取指定地标的平均评分
     * 
     * @param landmarkId 地标ID
     * @return 地标的平均评分
     */
    @Query("SELECT AVG(u.rating) FROM UserLandmarkInteraction u WHERE u.landmarkId = :landmarkId")
    Double getAverageRatingForLandmark(@Param("landmarkId") Long landmarkId);

    /**
     * 获取指定地标的点赞数
     * 
     * @param landmarkId 地标ID
     * @return 地标的点赞数
     */
    @Query("SELECT SUM(u.likes) FROM UserLandmarkInteraction u WHERE u.landmarkId = :landmarkId")
    Integer getTotalLikesForLandmark(@Param("landmarkId") Long landmarkId);

    /**
     * 获取指定地标的踩数
     * 
     * @param landmarkId 地标ID
     * @return 地标的踩数
     */
    @Query("SELECT SUM(u.dislikes) FROM UserLandmarkInteraction u WHERE u.landmarkId = :landmarkId")
    Integer getTotalDislikesForLandmark(@Param("landmarkId") Long landmarkId);
}
