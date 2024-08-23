package com.sysu.verto.storage.service;

import com.sysu.verto.storage.dao.FriendshipDAO;
import com.sysu.verto.storage.dao.FriendshipRepository;
import com.sysu.verto.storage.model.Friendship;
import com.sysu.verto.storage.model.UserCoreInfo;
import org.springframework.beans.factory.annotation.Autowired;
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
}
