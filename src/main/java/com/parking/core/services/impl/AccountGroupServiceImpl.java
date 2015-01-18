package com.parking.core.services.impl;

import com.parking.core.models.entities.AccountGroup;
import com.parking.core.models.entities.BlogEntry;
import com.parking.core.repositories.AccountRepo;
import com.parking.core.repositories.AccountGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.parking.core.services.AccountGroupService;
import com.parking.core.services.util.AccountGroupList;

@Service
@Transactional
public class AccountGroupServiceImpl implements AccountGroupService {

    @Autowired
    private AccountGroupRepo accountGroupRepo;

    @Autowired
    private AccountRepo accountRepo;

    @Override
    public AccountGroupList findAllGroups() {
        return new AccountGroupList(accountGroupRepo.findAllAccountGroups());
    }

    @Override
    public AccountGroup findGroup(Long id) {
        return accountGroupRepo.findAccountGroup(id);
    }

    @Override
    public AccountGroup findAccountGroupEntry(Long id) {
        return accountGroupRepo.findAccountGroup(id);
    }

    @Override
    public AccountGroup deleteAccountGroupEntry(Long id) {
        return accountGroupRepo.deleteAccountGroupEntry(id);
    }

    @Override
    public AccountGroup updateAccountGroupEntry(Long id, AccountGroup data) {
        return accountGroupRepo.updateAccountGroupEntry(id, data);
    }

}
