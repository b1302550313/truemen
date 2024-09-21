package com.sysu.verto.friendship.dao;

import org.springframework.context.annotation.Primary;

import com.sysu.verto.friendship.model.Friendship;

import java.util.List;

public interface FriendshipDAO {
    List<Friendship> findFriendsByWeChatOrPhone(String identifier);

    int getMutualFriendsCount(Long userId);

    int getFollowingCount(Long userId);

    int getFollowersCount(Long userId);

    List<Friendship> getMutualFriends(Long userId);

    List<Friendship> getFollowingFriends(Long userId);

    List<Friendship> getFollowers(Long userId);
}
