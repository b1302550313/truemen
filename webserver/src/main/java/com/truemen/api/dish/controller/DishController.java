package com.truemen.api.dish.controller;

import com.example.model.Dish;
import com.example.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/landmarks/{landmarkId}/dishes")
public class DishController {

    @Autowired
    private DishService dishService;

    // 获取推荐菜品
    @GetMapping
    public List<Dish> getDishesByLandmarkId(@PathVariable("landmarkId") int landmarkId) {
        return dishService.getDishesByLandmarkId(landmarkId);
    }

    // 添加推荐菜品
    @PostMapping
    public Dish addDish(@PathVariable("landmarkId") int landmarkId, @RequestBody Dish dish) {
        return dishService.addDish(landmarkId, dish);
    }
}
