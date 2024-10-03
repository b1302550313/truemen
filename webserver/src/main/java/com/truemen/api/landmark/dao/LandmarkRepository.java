package com.truemen.api.landmark.dao;

import com.truemen.api.landmark.model.Landmark;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LandmarkRepository extends JpaRepository<Landmark, Long> {

    /**
     * 根据名称查找地标
     * 
     * @param name 地标名称
     * @return 符合条件的所有地标列表
     */
    List<Landmark> findByName(String name);

    /**
     * 根据经纬度查找地标
     * 
     * @param latitude  纬度
     * @param longitude 经度
     * @return 符合条件的所有地标列表
     */
    List<Landmark> findByLatitudeAndLongitude(Double latitude, Double longitude);

    /**
     * 根据描述查找地标
     * 
     * @param keyword 关键词
     * @return 符合条件的所有地标列表
     */
    List<Landmark> findByDescriptionContaining(String keyword);

    /**
     * 根据名称和经纬度查找地标
     * 
     * @param name      地标名称
     * @param latitude  纬度
     * @param longitude 经度
     * @return 符合条件的所有地标列表
     */
    List<Landmark> findByNameAndLatitudeAndLongitude(String name, Double latitude, Double longitude);

    /**
     * 根据名称或经纬度查找地标
     * 
     * @param name      地标名称
     * @param latitude  纬度
     * @param longitude 经度
     * @return 符合条件的所有地标列表
     */
    List<Landmark> findByNameOrLatitudeOrLongitude(String name, Double latitude, Double longitude);
}
