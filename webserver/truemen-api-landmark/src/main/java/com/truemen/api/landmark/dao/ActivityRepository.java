package com.truemen.api.landmark.dao;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truemen.api.landmark.model.Activity;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    // 根据地标ID查找活动
    List<Activity> findByLandmarkId(Long landmarkId);

    // 根据日期范围查找活动
    List<Activity> findByStartDateLessThanEqualAndEndDateGreaterThanEqual(Date endDate, Date startDate);
}