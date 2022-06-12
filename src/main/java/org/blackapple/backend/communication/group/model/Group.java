package org.blackapple.backend.communication.group.model;


import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.blackapple.backend.authorization.model.Account;
import org.blackapple.backend.communication.task.model.Task;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "u_group")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "descript")
    private String description;
    @Column(name = "admin_id")
    private int adminId;

    @OneToMany(cascade = {CascadeType.PERSIST,CascadeType.MERGE} , fetch = FetchType.EAGER, targetEntity = Task.class, mappedBy = "group")
    @JsonBackReference
    private List<Task> tasks = new ArrayList<>();

    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinTable(name = "group_member",
            joinColumns = @JoinColumn(name = "group_id"),
            inverseJoinColumns = @JoinColumn(name = "member_id")
    )
    @JsonBackReference
    private List<Account> members = new ArrayList<>();
}
