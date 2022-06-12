package org.blackapple.backend.user.repositories;

import org.blackapple.backend.authorization.model.Account;
import org.blackapple.backend.communication.util.StateRequest;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends CrudRepository<Account, Integer> {

    @Query(nativeQuery = true, value = "select u.username from u_user u where u.id = :id")
    String getUsernameById(int id);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "insert into group_member (group_id, member_id, state) values (:groupId,:userId, :state) ")
    void addGroupToUser(int groupId, int userId, String state);

    List<Account> getAccountsByGroupsId(int groupId);
}
