package com.truemen.api.activity.service;

import com.truemen.api.activity.model.Activity;
import com.truemen.api.activity.repository.ActivityRepository;
import com.truemen.api.landmark.model.Landmark;
import com.truemen.api.landmark.service.LandmarkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    @Autowired
    private LandmarkService landmarkService;

    public Activity createActivity(Activity activity) {
        Long landmarkId = activity.getLocation().getId(); // 确保使用正确的 ID 获取地标
        Landmark landmark = landmarkService.getLandmarkById(landmarkId);
        if (landmark == null) {
            throw new IllegalArgumentException("关联的地标不存在，landmarkId: " + landmarkId);
        }
        activity.setLocation(landmark);
        return activityRepository.save(activity);
    }

    public Activity getActivityById(Long activityId) {
        return activityRepository.findById(activityId).orElse(null);
    }

    public List<Activity> getAllActivities() {
        return activityRepository.findAll();
    }

    public List<Activity> getActivitiesByLandmarkId(Long landmarkId) {
        return activityRepository.findByLocation_LandmarkId(landmarkId);
    }

    public List<Activity> getActivitiesByCreatedBy(String createdBy) {
        return activityRepository.findByCreatedBy(createdBy);
    }

    public List<Activity> searchActivitiesByKeyword(String keyword) {
        return activityRepository.findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(keyword, keyword);
    }

    public Activity updateActivity(Long activityId, Activity updatedActivity) {
        Optional<Activity> optionalActivity = activityRepository.findById(activityId);
        if (optionalActivity.isPresent()) {
            Activity existingActivity = optionalActivity.get();
            existingActivity.setTitle(updatedActivity.getTitle());
            existingActivity.setDescription(updatedActivity.getDescription());
            existingActivity.setStartTime(updatedActivity.getStartTime());
            existingActivity.setEndTime(updatedActivity.getEndTime());
            existingActivity.setCreatedBy(updatedActivity.getCreatedBy());

            if (updatedActivity.getLocation() != null) {
                Long newLandmarkId = updatedActivity.getLocation().getId();
                Landmark newLandmark = landmarkService.getLandmarkById(newLandmarkId);
                if (newLandmark != null) {
                    existingActivity.setLocation(newLandmark);
                } else {
                    throw new IllegalArgumentException("关联的地标不存在，landmarkId: " + newLandmarkId);
                }
            }

            return activityRepository.save(existingActivity);
        } else {
            return null;
        }
    }

    public boolean deleteActivity(Long activityId) {
        if (activityRepository.existsById(activityId)) {
            activityRepository.deleteById(activityId);
            return true;
        } else {
            return false;
        }
    }
}
