package com.truemen.api.activity.repository;

import com.truemen.api.activity.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByLocation_LandmarkId(Long landmarkId);

    List<Activity> findByCreatedBy(String createdBy);

    List<Activity> findByTitleContainingIgnoreCaseOrDescriptionContainingIgnoreCase(String title, String description);
}
