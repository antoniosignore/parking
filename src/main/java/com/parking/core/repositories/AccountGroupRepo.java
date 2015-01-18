package com.parking.core.repositories;


import com.parking.core.models.entities.AccountGroup;
import com.parking.core.models.entities.BlogEntry;

import java.util.List;
import java.util.Set;

public interface AccountGroupRepo {

    public AccountGroup createAccountGroup(AccountGroup data);

    public List<AccountGroup> findAllAccountGroups();

    public AccountGroup findAccountGroup(Long id);

    public AccountGroup findAccountGroupByName(String name);

    public AccountGroup deleteAccountGroupEntry(Long id);

    public AccountGroup updateAccountGroupEntry(Long id, AccountGroup data);


}
