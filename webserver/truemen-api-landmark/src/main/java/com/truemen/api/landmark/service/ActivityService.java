package com.truemen.api.landmark.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truemen.api.landmark.dao.ActivityRepository;
import com.truemen.api.landmark.model.Activity;

@Service
public class ActivityService {

    @Autowired
    private ActivityRepository activityRepository;

    // 根据地标ID获取活动列表
    public List<Activity> getActivitiesByLandmarkId(Long landmarkId) {
        return activityRepository.findByLandmarkId(landmarkId);
    }

    // 根据日期范围获取活动列表
    public List<Activity> getActivitiesByDateRange(Date startDate, Date endDate) {
        return activityRepository.findByStartDateLessThanEqualAndEndDateGreaterThanEqual(endDate, startDate);
    }
}