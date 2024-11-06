package com.truemen.api.dish.model;

import jakarta.persistence.*;
@Entity
public class Dish {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long landmarkId; // 地标ID
    private String name;     // 菜品名称
    private String description; // 菜品描述
    private Double price;    // 菜品价格
    private String imageUrl; // 菜品图片URL

    // 默认构造方法
    public Dish() {
    }

    // 带参构造方法
    public Dish(Long landmarkId, String name, String description, Double price, String imageUrl) {
        this.landmarkId = landmarkId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.imageUrl = imageUrl;
    }

    // Getter 和 Setter 方法
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getLandmarkId() {
        return landmarkId;
    }

    public void setLandmarkId(Long landmarkId) {
        this.landmarkId = landmarkId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }
}
