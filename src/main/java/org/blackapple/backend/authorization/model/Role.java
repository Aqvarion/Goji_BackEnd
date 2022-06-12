package org.blackapple.backend.authorization.model;

import javax.persistence.*;

@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(name = "rolename")
    private ERole name;

    public Role(ERole name) {
        this.name=name;
    }

    public Role() {
    }

    public ERole getName() {
        return name;
    }


    @Override
    public String toString() {
        return "Role{" +
                "id=" + id +
                ", name=" + name +
                '}';
    }
}
