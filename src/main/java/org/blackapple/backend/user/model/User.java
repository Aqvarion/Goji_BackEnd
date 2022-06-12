package org.blackapple.backend.user.model;

import lombok.Data;
import org.blackapple.backend.authorization.model.Account;

@Data
public class User {
    private int id;
    private String username;

    public User(Account account) {
        this.id=account.getId();
        this.username= account.getUsername();
    }
}
