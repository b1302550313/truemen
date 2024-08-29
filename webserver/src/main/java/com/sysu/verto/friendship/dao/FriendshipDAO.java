package com.sysu.verto.friendship.dao;

import org.springframework.context.annotation.Primary;

import com.sysu.verto.friendship.model.Friendship;

import java.util.List;

public interface FriendshipDAO {
    List<Friendship> findFriendsByWeChatOrPhone(String identifier);
}
