package org.blackapple.backend.user.freinds.model;

import org.blackapple.backend.communication.task.model.Task;

import javax.persistence.*;

@Entity
@Table(name = "friend")
public class Friend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "userID1")
    private int userId1;
    @Column(name = "userID2")
    private int userId2;

    public Friend(int userId1, int userId2) {
        this.userId1 = userId1;
        this.userId2 = userId2;
    }

    public Friend() {
    }
}
