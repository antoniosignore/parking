package com.parking.core.services.util;


import com.parking.core.models.entities.AccountGroup;

import java.util.ArrayList;
import java.util.List;

public class AccountGroupList {

    private List<AccountGroup> accountGroups = new ArrayList<AccountGroup>();

    public AccountGroupList(List<AccountGroup> resultList) {
        this.accountGroups = resultList;
    }

    public List<AccountGroup> getAccountGroups() {
        return accountGroups;
    }

    public void setAccountGroups(List<AccountGroup> accountGroups) {
        this.accountGroups = accountGroups;
    }
}
