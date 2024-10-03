package com.truemen.api.landmark.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.truemen.api.landmark.model.Activity;
import com.truemen.api.landmark.model.CalendarConfig;
import com.truemen.api.landmark.model.Landmark;
import com.truemen.api.landmark.service.ActivityService;
import com.truemen.api.landmark.service.LandmarkService;

@RestController
@RequestMapping("/api/v1/landmarks")
public class LandmarkController {

    @Autowired
    private LandmarkService landmarkService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private CalendarConfig calendarConfig;

    // 根据地标ID获取地标详细信息
    @GetMapping("/{landmarkId}")
    public ResponseEntity<Landmark> getLandmarkById(@PathVariable Long landmarkId) {
        Landmark landmark = landmarkService.getLandmarkById(landmarkId);
        if (landmark != null) {
            return ResponseEntity.ok(landmark);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // 根据地标ID获取活动列表
    @GetMapping("/{landmarkId}/activities")
    public ResponseEntity<List<Activity>> getActivitiesByLandmarkId(@PathVariable Long landmarkId) {
        List<Activity> activities = activityService.getActivitiesByLandmarkId(landmarkId);
        return ResponseEntity.ok(activities);
    }

    // 根据日期范围获取活动列表
    @GetMapping("/activities")
    public ResponseEntity<List<Activity>> getActivitiesByDateRange(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Activity> activities = activityService.getActivitiesByDateRange(startDate, endDate);
        return ResponseEntity.ok(activities);
    }

    // 获取日历配置信息
    @GetMapping("/calendar-config")
    public ResponseEntity<CalendarConfig> getCalendarConfig() {
        return ResponseEntity.ok(calendarConfig);
    }
}