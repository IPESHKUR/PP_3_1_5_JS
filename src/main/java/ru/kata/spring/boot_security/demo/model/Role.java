package ru.kata.spring.boot_security.demo.model;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table
public class Role implements GrantedAuthority {
    @Id
    @Column
    private Long id;
    @Column
    private String name;

    @ManyToMany
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "role_id", referencedColumnName="id")},
            inverseJoinColumns = {@JoinColumn(name = "user_id",referencedColumnName="id")})
    private Set<User> users = new HashSet<>();
    public Role() {}

    public Role(String name) {
        this.name = name;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    @Override
    public String getAuthority() {
        return name;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        if (name != null && name.length() != 0 && name.contains("ROLE")) {
            return name.substring(5);
        }
        return name;
    }
}


