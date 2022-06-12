package org.blackapple.backend.authorization.repository;

import org.blackapple.backend.authorization.model.Account;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
public interface AccountRepository extends CrudRepository<Account, Integer> {

    Optional<Account> findByUsername(String name);

    boolean existsByUsername(String username);

    boolean existsByEmail(String email);

    @Modifying
    @Transactional
    @Query(nativeQuery = true, value = "UPDATE u_user SET user_password=:newPassword WHERE id=:userId")
    void changePassword(int userId, String newPassword);
}
