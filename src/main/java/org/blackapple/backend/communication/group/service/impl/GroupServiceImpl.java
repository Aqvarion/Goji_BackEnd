package org.blackapple.backend.communication.group.service.impl;

import org.blackapple.backend.authorization.model.Account;
import org.blackapple.backend.communication.group.model.Group;
import org.blackapple.backend.communication.group.model.GroupDTO;
import org.blackapple.backend.communication.group.model.GroupMember;
import org.blackapple.backend.communication.group.model.GroupMemberId;
import org.blackapple.backend.communication.group.repositories.GroupMemberRepository;
import org.blackapple.backend.communication.group.repositories.GroupRepository;
import org.blackapple.backend.communication.group.service.GroupService;
import org.blackapple.backend.communication.util.StateRequest;
import org.blackapple.backend.user.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class GroupServiceImpl implements GroupService {
    @Autowired
    GroupRepository groupRepository;

    @Autowired
    GroupMemberRepository groupMemberRepository;

    @Autowired
    UserRepository userRepository;

    @Override
    public Iterable<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    @Override
    public List<Group> getGroupsByMemberId(int memberId) {
        return groupRepository.getGroupByMembersId(memberId);
    }

    public List<Account> getAllMembersOfGroupByGroupId(int groupId) {
        return userRepository.getAccountsByGroupsId(groupId);
    }

    @Override
    public List<Group> getAdminGroupsById(int adminId) {
        return groupRepository.getGroupByAdminId(adminId);
    }

    @Override
    public void createGroup(GroupDTO groupDTO) {
        Group group = new Group();
        group.setTitle(groupDTO.getTitle());
        group.setAdminId(groupDTO.getAdminId());
        group.setDescription(groupDTO.getDescription());
        groupRepository.save(group);
    }

    @Override
    public void inviteUser(int userId, int groupId) {
        Account user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findById(groupId).orElse(null);
        if (Objects.isNull(user) || Objects.isNull(group)) return;
        if (user.getGroups().contains(group)) {
            if (Objects.equals(StateRequest.request.toString(),
                    groupMemberRepository.getState(userId, groupId))) {
                groupMemberRepository.updateState(userId, groupId, StateRequest.member.toString());
            }
            return;
        }
        userRepository.addGroupToUser(groupId, userId, StateRequest.invited.toString());
    }

    @Override
    public void requestOfUserToGroup(int userId, int groupId) {
        Account user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findById(groupId).orElse(null);
        if (Objects.isNull(user) || Objects.isNull(group)) return;
        if (user.getGroups().contains(group)) {
            if (Objects.equals(StateRequest.invited.toString(), groupMemberRepository.getState(userId, groupId))) {
                groupMemberRepository.updateState(userId, groupId, StateRequest.member.toString());
            }
            return;
        }
        userRepository.addGroupToUser(groupId, userId, StateRequest.request.toString());
    }

    @Override
    public List<GroupMember> getGroupRequests(int adminId, int groupId) {
        List<GroupMember> groupMembers = new ArrayList<>();
        boolean isAdminOfGroup = groupRepository.getGroupByAdminId(adminId).stream().anyMatch(group -> Objects.equals(group.getAdminId(), adminId));
        if (!isAdminOfGroup) return groupMembers;
        groupMembers = groupMemberRepository.findAllByGroupId(groupId).stream()
                .filter(groupMember -> Objects.equals(groupMember.getState(), StateRequest.request.toString()))
                .collect(Collectors.toList());
        return groupMembers;
    }

    @Override
    public List<GroupMember> getUserRequests(int userId) {
        return groupMemberRepository.findAllByMemberId(userId).stream()
                .filter(groupMember -> Objects.equals(groupMember.getState(), StateRequest.invited.toString()))
                .collect(Collectors.toList());
    }

    @Override
    public void confirmRequest(int userId, int groupId, boolean accept) {
        Account user = userRepository.findById(userId).orElse(null);
        Group group = groupRepository.findById(groupId).orElse(null);
        GroupMemberId id = new GroupMemberId(groupId, userId);
        GroupMember groupMember = groupMemberRepository.findByGroupMemberId(id);
        if (Objects.isNull(user) || Objects.isNull(group)) return;

        if (Objects.nonNull(groupMember)) {
            if (!accept) {
                groupMemberRepository.deleteByGroupMemberId(id);
                return;
            }
            if (Objects.equals(groupMember.getState(), StateRequest.invited.toString())
                    || Objects.equals(groupMember.getState(), StateRequest.request.toString())) {
                groupMemberRepository.updateState(userId,groupId, StateRequest.member.toString());
            }

        }
    }
}
