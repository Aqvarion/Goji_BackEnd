package org.blackapple.backend.communication.task.model;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import org.blackapple.backend.communication.group.model.Group;

import javax.persistence.*;

@Data
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "title")
    private String title;
    @Column(name = "state")
    private String state;
    @Column(name = "executor")
    private int executor;
    @Column(name = "description")
    private String description;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonManagedReference
    private Group group;
}
