package com.truemen.api.friendship.service;

import com.truemen.api.friendship.dao.FriendshipDAO;
import com.truemen.api.friendship.dao.FriendshipRepository;
import com.truemen.api.friendship.model.Friendship;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FriendshipService {

    @Autowired
    private FriendshipDAO friendshipDAO;

    @Autowired
    private FriendshipRepository friendshipRepository;

    // 获取好友列表
    public List<Friendship> getFriends(String identifier) {
        return friendshipDAO.findFriendsByWeChatOrPhone(identifier);
    }

    // 删除好友关系
    public boolean deleteFriend(Long currentUserId, Long friendId) {
        List<Friendship> friendships = friendshipRepository.findByUserUidAndFriendId(currentUserId, friendId);
        if (!friendships.isEmpty()) {
            friendshipRepository.deleteAll(friendships);
            return true;
        }
        return false;
    }

    // 获取好友请求列表
    public List<Friendship> getFriendRequests(Long currentUserId) {
        return friendshipRepository.findFriendRequestsByUserId(currentUserId);
    }

    // 添加好友
    public boolean addFriend(Long currentUserId, Long friendId) {
        // 检查是否已经是好友
        List<Friendship> existingFriendship = friendshipRepository.findByUserUidAndFriendId(currentUserId, friendId);
        if (existingFriendship.isEmpty()) {
            // 创建新的好友关系
            Optional<Friendship> user = friendshipRepository.findById(currentUserId);
            if (user.isPresent()) {
                Friendship friendship = new Friendship();
                friendship.setUser(user.get().getUser());
                friendship.setFriendId(friendId);
                friendshipRepository.save(friendship);
                return true;
            }
        }
        return false;
    }

    // 获取双箭头好友数
    public int getMutualFriendsCount(Long userId) {
        return friendshipDAO.getMutualFriendsCount(userId);
    }

    // 获取你关注的好友数
    public int getFollowingCount(Long userId) {
        return friendshipDAO.getFollowingCount(userId);
    }

    // 获取关注你的好友数
    public int getFollowersCount(Long userId) {
        return friendshipDAO.getFollowersCount(userId);
    }

    // 获取双箭头好友列表
    public List<Friendship> getMutualFriends(Long userId) {
        return friendshipDAO.getMutualFriends(userId);
    }

    // 获取你关注的好友列表
    public List<Friendship> getFollowingFriends(Long userId) {
        return friendshipDAO.getFollowingFriends(userId);
    }

    // 获取关注你的好友列表
    public List<Friendship> getFollowers(Long userId) {
        return friendshipDAO.getFollowers(userId);
    }

}
