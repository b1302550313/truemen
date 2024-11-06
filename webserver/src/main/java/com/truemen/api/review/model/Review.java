package com.truemen.api.review.model;


import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long landmarkId;
    private String content;
    private int rating;
    private Long userId;

    // Getters and setters
}
