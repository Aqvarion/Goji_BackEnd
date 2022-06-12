package org.blackapple.backend.communication.group.repositories;

import org.blackapple.backend.communication.group.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupRepository extends JpaRepository<Group, Integer> {
    List<Group> getGroupByMembersId(int id);

    List<Group> getGroupByAdminId(int id);
}
