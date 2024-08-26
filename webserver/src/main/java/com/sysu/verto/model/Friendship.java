package com.sysu.verto.model;

import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
@Table(name = "friendship")
public class Friendship {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "uid")
    private UserCoreInfo user;

    @Column(name = "friend_id")
    private Long friendId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    // Constructors
    public Friendship() {}

    public Friendship(UserCoreInfo user, Long friendId, Timestamp createdAt) {
        this.user = user;
        this.friendId = friendId;
        this.createdAt = createdAt;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserCoreInfo getUser() {
        return user;
    }

    public void setUser(UserCoreInfo user) {
        this.user = user;
    }

    public Long getFriendId() {
        return friendId;
    }

    public void setFriendId(Long friendId) {
        this.friendId = friendId;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }
}
