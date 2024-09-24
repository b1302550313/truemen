package com.truemen.api.landmark.dao;

import com.truemen.api.landmark.model.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    // 根据名称查找地标
    List<Landmark> findByName(String name);

    // 根据经纬度查找地标
    List<Landmark> findByLatitudeAndLongitude(Double latitude, Double longitude);

    // 根据描述查找地标
    List<Landmark> findByDescriptionContaining(String keyword);

    // 根据名称和经纬度查找地标
    List<Landmark> findByNameAndLatitudeAndLongitude(String name, Double latitude, Double longitude);

    // 根据名称或经纬度查找地标
    List<Landmark> findByNameOrLatitudeOrLongitude(String name, Double latitude, Double longitude);
}
