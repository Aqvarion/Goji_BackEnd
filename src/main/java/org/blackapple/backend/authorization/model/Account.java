package org.blackapple.backend.authorization.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;
import org.blackapple.backend.communication.group.model.Group;
import org.blackapple.backend.user.freinds.model.Friend;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "u_user", uniqueConstraints = {
        @UniqueConstraint(columnNames = "username"),
        @UniqueConstraint(columnNames = "email")
})
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotBlank
    @Size(max = 20)
    @Column(name = "username")
    private String username;
    @NotBlank
    @Size(max = 50)
    @Column(name = "email")
    private String email;
    @NotBlank
    @Size(max = 120)
    @Column(name = "user_password")
    private String password;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable( name = "user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToMany(mappedBy = "members")
    @JsonBackReference
    private Set<Group> groups = new HashSet<>();

    public Account() {
    }

    public Account(String username, String email, String password) {
        this.username=username;
        this.email=email;
        this.password=password;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

}
