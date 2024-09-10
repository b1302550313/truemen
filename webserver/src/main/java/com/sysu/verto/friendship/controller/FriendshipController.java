package com.sysu.verto.friendship.controller;

import com.sysu.verto.friendship.model.Friendship;
import com.sysu.verto.friendship.service.FriendshipService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/friends")
public class FriendshipController {

    @Autowired
    private FriendshipService friendshipService;

    @PostMapping
    public ResponseEntity<String> addFriend(@RequestParam Long currentUserId, @RequestParam Long friendId) {
        boolean result = friendshipService.addFriend(currentUserId, friendId);
        if (result) {
            return ResponseEntity.ok("Friend request sent successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to send friend request.");
        }
    }

    @DeleteMapping("/{friendId}")
    public ResponseEntity<String> deleteFriend(@RequestParam Long currentUserId, @PathVariable Long friendId) {
        boolean result = friendshipService.deleteFriend(currentUserId, friendId);
        if (result) {
            return ResponseEntity.ok("Friend deleted successfully.");
        } else {
            return ResponseEntity.status(400).body("Failed to delete friend.");
        }
    }

    @GetMapping("/users/{userId}/friends")
    public ResponseEntity<List<Friendship>> getFriends(@PathVariable Long userId) {
        List<Friendship> friends = friendshipService.getFriends(String.valueOf(userId));
        return ResponseEntity.ok(friends);
    }

    @GetMapping("/requests")
    public ResponseEntity<List<Friendship>> getFriendRequests(@RequestParam Long currentUserId) {
        List<Friendship> requests = friendshipService.getFriendRequests(currentUserId);
        return ResponseEntity.ok(requests);
    }

    // 获取好友数
    @GetMapping("/{userId}/friend-counts")
    public ResponseEntity<Map<String, Integer>> getFriendCounts(@PathVariable Long userId) {
        int mutualFriendsCount = friendshipService.getMutualFriendsCount(userId);
        int followingCount = friendshipService.getFollowingCount(userId);
        int followersCount = friendshipService.getFollowersCount(userId);

        Map<String, Integer> counts = new HashMap<>();
        counts.put("mutualFriends", mutualFriendsCount);
        counts.put("following", followingCount);
        counts.put("followers", followersCount);

        return ResponseEntity.ok(counts);
    }

    // 获取双箭头好友列表
    @GetMapping("/{userId}/mutual-friends")
    public ResponseEntity<List<Friendship>> getMutualFriends(@PathVariable Long userId) {
        List<Friendship> mutualFriends = friendshipService.getMutualFriends(userId);
        return ResponseEntity.ok(mutualFriends);
    }

    // 获取你关注的好友列表
    @GetMapping("/{userId}/following")
    public ResponseEntity<List<Friendship>> getFollowingFriends(@PathVariable Long userId) {
        List<Friendship> followingFriends = friendshipService.getFollowingFriends(userId);
        return ResponseEntity.ok(followingFriends);
    }

    // 获取关注你的好友列表
    @GetMapping("/{userId}/followers")
    public ResponseEntity<List<Friendship>> getFollowers(@PathVariable Long userId) {
        List<Friendship> followers = friendshipService.getFollowers(userId);
        return ResponseEntity.ok(followers);
    }
}
