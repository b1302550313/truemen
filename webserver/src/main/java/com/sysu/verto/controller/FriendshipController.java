package com.sysu.verto.controller;

import com.sysu.verto.model.Friendship;
import com.sysu.verto.service.FriendshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
}
