package com.truemen.api.guide.service;

import com.truemen.api.guide.model.Guide;
import com.truemen.api.guide.repository.GuideRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GuideService {

    @Autowired
    private GuideRepository guideRepository;

    public Guide createGuide(Guide guide) {
        return guideRepository.save(guide);
    }
}
