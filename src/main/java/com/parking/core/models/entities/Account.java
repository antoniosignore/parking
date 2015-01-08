package com.parking.core.models.entities;

import com.parking.core.models.entities.parking.Group;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String password;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name="ACCOUNT_GROUP",
            joinColumns={@JoinColumn(name="ACCOUNT_ID")},
            inverseJoinColumns={@JoinColumn(name="GROUP_ID")})
    private Set<Group> groups = new HashSet<Group>();

    public Set<Group> getGroups() {
        return groups;
    }

    public void setGroups(Set<Group> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;

        Account account = (Account) o;

        if (!id.equals(account.id)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
