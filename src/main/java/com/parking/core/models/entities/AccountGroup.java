package com.parking.core.models.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(uniqueConstraints= @UniqueConstraint(columnNames = {"groupName"}))
public class AccountGroup implements Serializable {

    @Id
    @GeneratedValue
    private Long id;

    @NotNull
    private String groupName;

    private String groupDesc;

    @ManyToMany(mappedBy="accountGroups")
    private List<Account> accounts = new ArrayList<Account>();

    public AccountGroup() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getGroupDesc() {
        return groupDesc;
    }

    public void setGroupDesc(String groupDesc) {
        this.groupDesc = groupDesc;
    }

    public void addAccount(Account account){
        accounts.add(account);
    }

    public void removeAccount(Account account){
        accounts.remove(account);
    }

    public List<Account> getAccounts() {
        return accounts;
    }
}