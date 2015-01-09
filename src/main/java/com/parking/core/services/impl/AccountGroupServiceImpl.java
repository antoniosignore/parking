package com.parking.core.services.impl;

import com.parking.core.models.entities.Account;
import com.parking.core.models.entities.AccountGroup;
import com.parking.core.repositories.AccountRepo;
import com.parking.core.repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.parking.core.services.AccountGroupService;
import com.parking.core.services.util.AccountGroupList;

@Service
@Transactional
public class AccountGroupServiceImpl implements AccountGroupService {

    @Autowired
    private GroupRepo groupRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public AccountGroupList findAllGroups() {
        return new AccountGroupList(groupRepo.findAllGroups());
    }


    @Override
    public AccountGroup findGroup(Long id) {
        return groupRepo.findGroup(id);
    }
}
