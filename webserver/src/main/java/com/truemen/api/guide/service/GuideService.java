package com.truemen.api.guide.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.truemen.api.guide.model.Day;
import com.truemen.api.guide.model.Guide;
import com.truemen.api.guide.repository.GuideRepository;

@Service
public class GuideService {

    @Autowired
    private GuideRepository guideRepository;

    // 获取某个具体攻略
    public Guide getGuideById(Long id) {
        return guideRepository.findById(id).orElse(null);
    }

    // 更新某个具体攻略
    public Guide updateGuide(Long id, Guide guide) {
        Guide existingGuide = guideRepository.findById(id).orElse(null);
        if (existingGuide != null) {
            existingGuide.setTitle(guide.getTitle());
            existingGuide.setDescription(guide.getDescription());
            existingGuide.setTags(guide.getTags());
            existingGuide.setDays(guide.getDays());
            return guideRepository.save(existingGuide);
        }
        return null;
    }

    // 创建攻略
    public Guide createGuide(Guide guide) {
        return guideRepository.save(guide);
    }

    // 点击+按钮后增加一天
    public Guide addDayToGuide(Long id) {
        Guide guide = guideRepository.findById(id).orElse(null);
        if (guide != null) {
            List<Day> days = guide.getDays();
            if (days == null) {
                days = new ArrayList<>();
            }
            int nextDayNumber = days.size() + 1;
            Day newDay = new Day();
            newDay.setDayNumber(nextDayNumber);
            days.add(newDay);
            guide.setDays(days);
            return guideRepository.save(guide);
        }
        return null;
    }
}