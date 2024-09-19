package com.sysu.verto.landmark.controller;

import com.sysu.verto.landmark.model.Landmark;
import com.sysu.verto.landmark.service.LandmarkService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/landmarks")
public class LandmarkController {

    @Autowired
    private LandmarkService landmarkService;

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
}