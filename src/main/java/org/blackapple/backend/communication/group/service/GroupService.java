package org.blackapple.backend.communication.group.service;

import org.blackapple.backend.authorization.model.Account;
import org.blackapple.backend.communication.group.model.Group;
import org.blackapple.backend.communication.group.model.GroupDTO;
import org.blackapple.backend.communication.group.model.GroupMember;
import org.blackapple.backend.user.model.User;

import java.util.List;

public interface GroupService {

    Iterable<Group> getAllGroups();

    List<Group> getGroupsByMemberId(int id);

    List<Account> getAllMembersOfGroupByGroupId(int groupId);

    List<Group> getAdminGroupsById(int userId);

    void createGroup(GroupDTO groupDTO);

    void inviteUser(int userId, int groupId);

    void requestOfUserToGroup(int userId, int groupId);

    List<GroupMember> getGroupRequests(int adminId, int groupId);

    List<GroupMember> getUserRequests(int userId);

    void confirmRequest(int userId, int groupId, boolean accept);
}
