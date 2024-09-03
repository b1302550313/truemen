package com.sysu.verto.friendship.dao;

import com.sysu.verto.friendship.model.Friendship;
import com.sysu.verto.friendship.model.UserCoreInfo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendshipRepository extends JpaRepository<Friendship, Long> {

    List<Friendship> findByUserUidAndFriendId(Long userId, Long friendId);

    @Query("SELECT f.user FROM Friendship f WHERE f.friendId = :userId")
    List<UserCoreInfo> findFriendsByUserId(@Param("userId") Long userId);

    @Query("SELECT f FROM Friendship f WHERE f.user.uid = :userId AND f.friendId IS NULL")
    List<Friendship> findFriendRequestsByUserId(@Param("userId") Long userId);
}
