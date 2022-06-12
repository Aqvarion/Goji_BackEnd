package org.blackapple.backend.user.freinds.repositories;

import org.blackapple.backend.user.freinds.model.Friend;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendsRepository extends CrudRepository<Friend,Integer> {
    @Query(nativeQuery = true,value = "SELECT * FROM friend " +
            "WHERE userID1 =:userId1 and userID2 =:userId2 " +
            "OR userID1 =:userId2 and userID2 =:userId1")
    int findFriendsId(int userId1, int userId2);

    @Transactional
    @Modifying
    @Query(nativeQuery = true,value = "UPDATE friend SET confirmed=:state WHERE id=:id")
    void updateConfirm(int id, boolean state);

    @Query(nativeQuery = true,value = "SELECT userID1 FROM (SELECT userID1,confirmed FROM friend WHERE userID2=:id " +
            "UNION SELECT userID2, confirmed FROM friend WHERE userID1=:id) as friends WHERE confirmed=true")
    List<Integer> findUserFriendsById(int id);


}
