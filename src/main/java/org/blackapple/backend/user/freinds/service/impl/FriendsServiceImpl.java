package org.blackapple.backend.user.freinds.service.impl;

import org.blackapple.backend.authorization.repository.AccountRepository;
import org.blackapple.backend.user.freinds.model.Friend;
import org.blackapple.backend.user.freinds.repositories.FriendsRepository;
import org.blackapple.backend.user.freinds.service.FriendsService;
import org.blackapple.backend.user.model.User;
import org.blackapple.backend.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private FriendsRepository friendsRepository;

    @Override
    public void createFriendRequest(int userId1, int userId2) {
//        int idFriends = friendsRepository.findFriendsId(userId1,userId2);
////        if (idFriends) return;

        Friend friend = new Friend(userId1,userId2);
        friendsRepository.save(friend);
    }

    @Override
    public void confirmFriendRequest(int userId1, int userId2) {
        int idFriends = friendsRepository.findFriendsId(userId1, userId2);
        friendsRepository.updateConfirm(idFriends, true);
    }

    @Override
    public void deleteFriends(int userId1, int userId2) {
        int idFriends = friendsRepository.findFriendsId(userId1, userId2);
        friendsRepository.deleteById(idFriends);
    }

    @Override
    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(account -> users.add(new User(account)));
        return users;
    }

    @Override
    public List<User> getMyFriends(int userId) {
        List<Integer> friendsId = friendsRepository.findUserFriendsById(userId);
        List<User> friends = new ArrayList<>();
        userRepository.findAllById(friendsId).forEach(account -> friends.add(new User(account)));
        return friends;
    }
}
