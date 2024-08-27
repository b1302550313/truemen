package com.sysu.verto.dao;

import com.sysu.verto.model.Friendship;
import org.springframework.context.annotation.Primary;

import java.util.List;

public interface FriendshipDAO {
    List<Friendship> findFriendsByWeChatOrPhone(String identifier);
}
