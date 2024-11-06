package com.truemen.api.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserLocation {
    private String userId;
    private String avatarUrl;
    private Double latitude;
    private Double longitude;
}
