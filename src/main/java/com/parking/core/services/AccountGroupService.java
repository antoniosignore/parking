package com.parking.core.services;

import com.parking.core.models.entities.AccountGroup;
import com.parking.core.services.util.AccountGroupList;

public interface AccountGroupService {

    public AccountGroupList findAllGroups();

    public AccountGroupList findAllGroupsByAccount(Long accountId);

    public AccountGroup findGroup(Long id);

}
