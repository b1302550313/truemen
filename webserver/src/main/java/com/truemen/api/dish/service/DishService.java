package com.truemen.api.dish.service;

import com.truemen.api.dish.model.Dish;
import com.truemen.api.dish.repository.DishRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DishService {

    @Autowired
    private DishRepository dishRepository;

    /**
     * 根据地标ID获取菜品列表
     *
     * @param landmarkId 地标ID
     * @return 菜品列表
     */
    public List<Dish> getDishesByLandmarkId(Long landmarkId) {
        return dishRepository.findByLandmarkId(landmarkId);
    }

    /**
     * 添加推荐菜品
     *
     * @param landmarkId 地标ID
     * @param dish 菜品
     * @return 添加后的菜品
     */
    public Dish addDish(Long landmarkId, Dish dish) {
        dish.setLandmarkId(landmarkId); // 设置地标ID
        return dishRepository.save(dish); // 保存菜品
    }
}
