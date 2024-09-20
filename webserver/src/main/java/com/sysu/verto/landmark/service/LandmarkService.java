package com.sysu.verto.landmark.service;

import com.sysu.verto.landmark.model.Landmark;
import com.sysu.verto.landmark.dao.LandmarkRepository; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class LandmarkService {

    @Autowired
    private LandmarkRepository landmarkRepository;

    // 根据ID获取地标
    public Landmark getLandmarkById(Long landmarkId) {
        Optional<Landmark> landmark = landmarkRepository.findById(landmarkId);
        return landmark.orElse(null); // 如果未找到，返回null
    }
}
