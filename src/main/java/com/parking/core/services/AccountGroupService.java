package com.parking.core.services;

import com.parking.core.models.entities.AccountGroup;
import com.parking.core.services.util.AccountGroupList;

public interface AccountGroupService {

    public AccountGroupList findAllGroups();

    public AccountGroup findGroup(Long id);

    public AccountGroup findAccountGroupEntry(Long id);

    public AccountGroup deleteAccountGroupEntry(Long id);

    public AccountGroup updateAccountGroupEntry(Long id, AccountGroup data);
}
