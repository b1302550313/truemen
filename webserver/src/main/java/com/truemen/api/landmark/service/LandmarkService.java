package com.truemen.api.landmark.service;

import com.truemen.api.landmark.model.Landmark;
import com.truemen.api.landmark.model.UserLandmarkInteraction;
import com.truemen.api.landmark.dao.LandmarkRepository;
import com.truemen.api.landmark.dao.UserLandmarkInteractionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.redis.core.StringRedisTemplate;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.springframework.scheduling.annotation.Scheduled;

@Service
@Transactional
public class LandmarkService {

    @Autowired
    private LandmarkRepository landmarkRepository;

    @Autowired
    private UserLandmarkInteractionRepository interactionRepository;

    @Autowired
    private StringRedisTemplate redisTemplate;

    // 根据ID获取地标
    public Landmark getLandmarkById(Long landmarkId) {
        Optional<Landmark> landmark = landmarkRepository.findById(landmarkId);
        return landmark.orElse(null); // 如果未找到，返回null
    }

    public boolean toggleFavorite(Long landmarkId, Long userId) {
        UserLandmarkInteraction interaction = interactionRepository.findByUserIdAndLandmarkId(userId, landmarkId)
                .orElse(new UserLandmarkInteraction(userId, landmarkId));
        interaction.setIsFavorite(!interaction.getIsFavorite());
        interactionRepository.save(interaction);
        return interaction.getIsFavorite();
    }

    public void likeOrDislike(Long landmarkId, Long userId, int likeStatus) {
        UserLandmarkInteraction interaction = interactionRepository.findByUserIdAndLandmarkId(userId, landmarkId)
                .orElse(new UserLandmarkInteraction(userId, landmarkId));
        int oldStatus = interaction.getLikeStatus();
        interaction.setLikeStatus(likeStatus);
        interactionRepository.save(interaction);

        Landmark landmark = landmarkRepository.findById(landmarkId).orElseThrow();
        if (oldStatus != likeStatus) {
            if (likeStatus == 1)
                landmark.setLikes(landmark.getLikes() + 1);
            else if (likeStatus == -1)
                landmark.setDislikes(landmark.getDislikes() + 1);
            if (oldStatus == 1)
                landmark.setLikes(landmark.getLikes() - 1);
            else if (oldStatus == -1)
                landmark.setDislikes(landmark.getDislikes() - 1);
        }
        landmarkRepository.save(landmark);
    }

    public void rateLandmark(Long landmarkId, Long userId, int rating) {
        UserLandmarkInteraction interaction = interactionRepository.findByUserIdAndLandmarkId(userId, landmarkId)
                .orElse(new UserLandmarkInteraction(userId, landmarkId));
        interaction.setRating(rating);
        interactionRepository.save(interaction);

        // 更新地标的平均评分
        double avgRating = interactionRepository.getAverageRatingForLandmark(landmarkId);
        Landmark landmark = landmarkRepository.findById(landmarkId).orElseThrow();
        landmark.setAverageRating(avgRating);
        landmarkRepository.save(landmark);
    }

    public void updateLikes(Long landmarkId, Long userId, boolean isLike) {
        UserLandmarkInteraction interaction = interactionRepository.findByUserIdAndLandmarkId(userId, landmarkId)
                .orElse(new UserLandmarkInteraction(userId, landmarkId));

        int oldLikeStatus = interaction.getLikeStatus();
        interaction.setLikeStatus(isLike ? 1 : -1);
        interactionRepository.save(interaction);

        String key = "landmark:likes:" + landmarkId;
        if (oldLikeStatus == 0) {
            redisTemplate.opsForValue().increment(key, isLike ? 1 : -1);
        } else if (oldLikeStatus != (isLike ? 1 : -1)) {
            redisTemplate.opsForValue().increment(key, isLike ? 2 : -2);
        }
    }

    public void updateRating(Long landmarkId, Long userId, int rating) {
        UserLandmarkInteraction interaction = interactionRepository.findByUserIdAndLandmarkId(userId, landmarkId)
                .orElse(new UserLandmarkInteraction(userId, landmarkId));

        interaction.setRating(rating);
        interactionRepository.save(interaction);

        Double averageRating = interactionRepository.getAverageRatingForLandmark(landmarkId);
        String key = "landmark:rating:" + landmarkId;
        redisTemplate.opsForValue().set(key, String.valueOf(averageRating));
    }

    @Scheduled(fixedRate = 300000) // 每5分钟执行一次
    public void syncRedisToDatabase() {
        Set<String> likeKeys = redisTemplate.keys("landmark:likes:*");
        for (String key : likeKeys) {
            Long landmarkId = Long.parseLong(key.split(":")[2]);
            Integer likes = Integer.parseInt(redisTemplate.opsForValue().get(key));
            Landmark landmark = landmarkRepository.findById(landmarkId).orElse(null);
            if (landmark != null) {
                landmark.setLikes(likes);
                landmarkRepository.save(landmark);
            }
        }

        Set<String> ratingKeys = redisTemplate.keys("landmark:rating:*");
        for (String key : ratingKeys) {
            Long landmarkId = Long.parseLong(key.split(":")[2]);
            Double rating = Double.parseDouble(redisTemplate.opsForValue().get(key));
            Landmark landmark = landmarkRepository.findById(landmarkId).orElse(null);
            if (landmark != null) {
                landmark.setAverageRating(rating);
                landmarkRepository.save(landmark);
            }
        }
    }
}
