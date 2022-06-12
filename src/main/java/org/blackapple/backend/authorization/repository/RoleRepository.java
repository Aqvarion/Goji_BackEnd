package org.blackapple.backend.authorization.repository;

import org.blackapple.backend.authorization.model.ERole;
import org.blackapple.backend.authorization.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
