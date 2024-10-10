package com.truemen.api.guide.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.truemen.api.guide.model.Guide;

@Repository
public interface GuideRepository extends JpaRepository<Guide, Long> {
}