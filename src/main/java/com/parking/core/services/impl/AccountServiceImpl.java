package com.parking.core.services.impl;

import com.parking.core.models.entities.AccountGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.parking.core.models.entities.Account;
import com.parking.core.models.entities.Blog;
import com.parking.core.repositories.*;
import com.parking.core.services.AccountService;
import com.parking.core.services.exceptions.*;
import com.parking.core.services.util.AccountList;
import com.parking.core.services.util.BlogList;
import com.parking.core.services.util.GroupList;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private GroupRepo groupRepo;

    @Override
    public Account findAccount(Long id) {
        return accountRepo.findAccount(id);
    }

    @Override
    public Account createAccount(Account data) {
        Account account = accountRepo.findAccountByName(data.getName());
        if (account != null) {
            throw new AccountExistsException();
        }
        return accountRepo.createAccount(data);
    }

    @Override
    public Blog createBlog(Long accountId, Blog data) {
        Blog blogSameTitle = blogRepo.findBlogByTitle(data.getTitle());

        if (blogSameTitle != null) {
            throw new BlogExistsException();
        }

        Account account = accountRepo.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }

        Blog createdBlog = blogRepo.createBlog(data);

        createdBlog.setOwner(account);

        return createdBlog;
    }

    @Override
    public AccountGroup createGroup(Long accountId, AccountGroup accountGroup) {

        Account account = accountRepo.findAccount(accountId);
        if (account == null) throw new AccountDoesNotExistException();

        accountGroup.addAccount(account);

        AccountGroup group = null;
        try {
            group = groupRepo.createGroup(accountGroup);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GroupExistsException();
        }
        return group;
    }

    @Override
    public BlogList findBlogsByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        return new BlogList(blogRepo.findBlogsByAccount(accountId));
    }

    @Override
    public GroupList findGroupsByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        return new GroupList(account.getAccountGroups());
    }

    @Override
    public AccountList findAllAccounts() {
        return new AccountList(accountRepo.findAllAccounts());
    }

    @Override
    public Account findByAccountName(String name) {
        return accountRepo.findAccountByName(name);
    }
}
