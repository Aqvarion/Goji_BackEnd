package org.blackapple.backend.communication.group.controllers;

import org.blackapple.backend.authorization.model.Account;
import org.blackapple.backend.authorization.payload.MessageResponse;
import org.blackapple.backend.communication.group.model.Group;
import org.blackapple.backend.communication.group.model.GroupDTO;
import org.blackapple.backend.communication.group.model.GroupMember;
import org.blackapple.backend.communication.group.repositories.GroupMemberRepository;
import org.blackapple.backend.communication.group.service.GroupService;
import org.blackapple.backend.user.message.service.MessageDBWriter;
import org.blackapple.backend.user.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private GroupMemberRepository groupMemberRepository;

    @GetMapping("/all")
    public ResponseEntity<Iterable<Group>> getAllGroups() {
        return ResponseEntity.ok(groupService.getAllGroups());
    }

    @GetMapping("/my_groups")
    public ResponseEntity<List<Group>> getAllGroupsById(@RequestParam int userId) {
        return ResponseEntity.ok(groupService.getGroupsByMemberId(userId));
    }

    @GetMapping("/members_group")
    public ResponseEntity<List<Account>> getAllMembersOfGroupByGroupId(@RequestParam int groupId) {
        return ResponseEntity.ok(groupService.getAllMembersOfGroupByGroupId(groupId));
    }

    @GetMapping("my_admin_groups")
    public ResponseEntity<List<Group>> getAllAdminGroupsById(@RequestParam int userId) {
        return ResponseEntity.ok(groupService.getAdminGroupsById(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<MessageResponse> createGroup(@RequestBody GroupDTO groupDTO) {
        groupService.createGroup(groupDTO);
        return ResponseEntity.ok(new MessageResponse("Group created"));
    }

    //?????????????? ???????????? ???? ?????????????????????????? ?? ????????????
    @PostMapping("/user_request")
    public ResponseEntity<MessageResponse> requestOfUserToGroup(@RequestParam int userId, @RequestParam int groupId) {
        groupService.requestOfUserToGroup(userId, groupId);
        return ResponseEntity.ok(new MessageResponse("You have joined group!"));
    }

    //???????????????????? ?????????????????? ?? ????????????
    @PostMapping("/user_invite")
    public ResponseEntity<MessageResponse> inviteMembers(@RequestParam int userId, @RequestParam int groupId) {
        groupService.inviteUser(userId, groupId);
        return ResponseEntity.ok(new MessageResponse("You invited users"));
    }

    //?????????????????????? ???????????? ?? ????????????
    @GetMapping("/group_requests")
    public ResponseEntity<List<GroupMember>> getGroupRequests(@RequestParam int adminId, @RequestParam int groupId) {
            return ResponseEntity.ok(groupService.getGroupRequests(adminId, groupId));
    }

    //?????????????????????? ????????????-?????????????????????? ?????? ???????????????????????? ?? ????????????
    @GetMapping("/user_requests")
    public ResponseEntity<List<GroupMember>> getUserRequests(@RequestParam int userId) {
        return ResponseEntity.ok(groupService.getUserRequests(userId));
    }

    //?????????????????????? ???????????? ?? ????????????
    @PostMapping("/confirm_group_request")
    public ResponseEntity<MessageResponse> confirmGroupRequestFromUser(@RequestParam int userId, @RequestParam int groupId, @RequestParam boolean accept) {
        groupService.confirmRequest(userId,groupId, accept);
        return ResponseEntity.ok(new MessageResponse("Request has been confirm"));
    }

    //?????????????? ???????????? ?? ????????????
    @PostMapping("/confirm_user_request")
    public ResponseEntity<MessageResponse> confirmRequestToUser(@RequestParam int userId, @RequestParam int groupId, @RequestParam boolean accept) {
        groupService.confirmRequest(userId, groupId, accept);
        return ResponseEntity.ok(new MessageResponse("Request has been confirm"));
    }
}
