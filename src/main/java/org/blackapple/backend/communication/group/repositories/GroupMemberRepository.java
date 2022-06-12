package org.blackapple.backend.communication.group.repositories;

import org.blackapple.backend.communication.group.model.GroupMember;
import org.blackapple.backend.communication.group.model.GroupMemberId;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Repository
public interface GroupMemberRepository extends CrudRepository<GroupMember, GroupMemberId> {

    List<GroupMember> findAllByGroupId(int groupId);

    List<GroupMember> findAllByMemberId(int memberId);

    GroupMember findByGroupMemberId(GroupMemberId groupMemberId);

    void deleteByGroupMemberId(GroupMemberId groupMemberId);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE group_member SET state =:state WHERE group_id =:groupId AND member_id =:userId")
    void updateState(int userId, int groupId, String state);


    @Query(nativeQuery = true, value = "SELECT state from group_member WHERE group_id =:groupId AND member_id =:memberId")
    String getState(int memberId, int groupId);
}
