package com.truemen.api.dish.repository;

import com.truemen.api.dish.model.Dish;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DishRepository extends JpaRepository<Dish, Long> {

    // 根据地标ID查询菜品
    List<Dish> findByLandmarkId(Long landmarkId);
}
