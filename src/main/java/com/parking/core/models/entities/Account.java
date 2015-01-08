package com.parking.core.models.entities;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"name"}))
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
    @JoinTable(name="account_group",
            joinColumns={@JoinColumn(name="account_id")},
            inverseJoinColumns={@JoinColumn(name="group_id")})
    private List<AccountGroup> accountGroups = new ArrayList<AccountGroup>();

    public List<AccountGroup> getAccountGroups() {
        return accountGroups;
    }

    public void addAccountGroup(AccountGroup group){
        accountGroups.add(group);
    }

    public void removeAccountGroup(AccountGroup group){
        accountGroups.remove(group);
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
