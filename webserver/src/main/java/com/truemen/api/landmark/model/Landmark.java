package com.truemen.api.landmark.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;

/**
 * 地标实体类
 * 用于表示和存储地标信息
 */
@Entity
@Table(name = "landmark")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Landmark {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "landmark_id") // 使用数据库中的列名
    private Long landmarkId; // 地标ID，主键

    private String name; // 地标名称
    private String description; // 地标描述
    private String address; // 地址
    private String city; // 城市
    private String province; // 省份
    private String country; // 国家
    private Double latitude; // 纬度
    @Getter
    private Double longitude; // 经度
    private Integer category; // 类别
    private String iconPath; // 图标路径
    private String photoUrl; // 图片URL
    private String openingHours; // 营业时间
    private String contactInfo; // 联系信息
    private String website; // 官方网站
    private String status; // 状态

    // 新增字段
    @Column(name = "likes")
    private Integer likes = 0; // 点赞数

    @Column(name = "dislikes")
    private Integer dislikes = 0; // 踩数

    @Column(name = "average_rating")
    private Double averageRating = 0.0; // 平均评分

    // 更新评分和评论数量字段
    @Column(name = "rating")
    private Double rating = 0.0; // 评分

    @Column(name = "review_count")
    private Integer reviewCount = 0; // 评论数量

    /**
     * 增加点赞数
     */
    public void incrementLikes() {
        this.likes++;
    }

    /**
     * 减少点赞数
     */
    public void decrementLikes() {
        if (this.likes > 0) {
            this.likes--;
        }
    }

    /**
     * 增加踩数
     */
    public void incrementDislikes() {
        this.dislikes++;
    }

    /**
     * 减少踩数
     */
    public void decrementDislikes() {
        if (this.dislikes > 0) {
            this.dislikes--;
        }
    }

    /**
     * 更新平均评分
     * 
     * @param newRating 新的评分
     */
    public void updateAverageRating(Double newRating) {
        this.reviewCount++;
        this.averageRating = ((this.averageRating * (this.reviewCount - 1)) + newRating) / this.reviewCount;
        this.rating = this.averageRating; // 保持 rating 和 averageRating 一致
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getIconPath() {
        return iconPath;
    }

    public void setIconPath(String iconPath) {
        this.iconPath = iconPath;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    public String getOpeningHours() {
        return openingHours;
    }

    public void setOpeningHours(String openingHours) {
        this.openingHours = openingHours;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
