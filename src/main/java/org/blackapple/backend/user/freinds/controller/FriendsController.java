package org.blackapple.backend.user.freinds.controller;

import org.blackapple.backend.authorization.model.Account;
import org.blackapple.backend.authorization.payload.MessageResponse;
import org.blackapple.backend.user.freinds.model.Friend;
import org.blackapple.backend.user.freinds.service.impl.FriendsServiceImpl;
import org.blackapple.backend.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/friends")
public class FriendsController {

    @Autowired
    private FriendsServiceImpl friendsService;

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createFriends(@RequestParam int userId1, @RequestParam int userId2) {
        friendsService.createFriendRequest(userId1, userId2);
        return ResponseEntity.ok(new MessageResponse("Request has been sent"));
    }

    @PostMapping("/confirm")
    public ResponseEntity<MessageResponse> confirmRequest(@RequestParam int userId1, @RequestParam int userId2){
        friendsService.confirmFriendRequest(userId1, userId2);
        return ResponseEntity.ok(new MessageResponse("Request confirmed"));
    }

    @DeleteMapping("")
    public ResponseEntity<MessageResponse> deleteFriends(@RequestParam int userId1, @RequestParam int userId2){
        friendsService.deleteFriends(userId1, userId2);
        return ResponseEntity.ok(new MessageResponse("Friend deleted"));
    }

    @GetMapping("/users")
    public ResponseEntity<List<User>> getAllUsers(){
        return ResponseEntity.ok(friendsService.getAllUsers());
    }

    @GetMapping("/my-friends")
    public ResponseEntity<List<User>> getMyFriends(@RequestParam int userId){
        return ResponseEntity.ok(friendsService.getMyFriends(userId));
    }
}
