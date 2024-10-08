package com.truemen.api.guide.controller;

import com.truemen.api.guide.model.Guide;
import com.truemen.api.guide.service.GuideService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/guides")
public class GuideController {

    @Autowired
    private GuideService guideService;

    @PostMapping("/create")
    public ResponseEntity<Guide> createGuide(@RequestBody Guide guide) {
        Guide savedGuide = guideService.createGuide(guide);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedGuide);
    }
}
