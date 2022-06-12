package org.blackapple.backend.user.freinds.service;


import org.blackapple.backend.user.model.User;

import java.util.List;

public interface FriendsService {

    void createFriendRequest(int userId1, int userId2);

    void confirmFriendRequest(int userId1, int userId2);

    void deleteFriends(int userId1, int userId2);

    List<User> getAllUsers();

    List<User> getMyFriends(int userId);
}
