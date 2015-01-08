package com.parking.core.repositories;


import com.parking.core.models.entities.AccountGroup;

import java.util.List;
import java.util.Set;

public interface GroupRepo {

    public AccountGroup createGroup(AccountGroup data);

    public List<AccountGroup> findAllGroups();

    public AccountGroup findGroup(Long id);

    public AccountGroup findGroupByName(String name);

}
