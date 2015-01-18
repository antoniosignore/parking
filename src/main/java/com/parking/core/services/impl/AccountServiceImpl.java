package com.parking.core.services.impl;

import com.parking.core.models.entities.*;
import com.parking.core.services.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.parking.core.repositories.*;
import com.parking.core.services.AccountService;
import com.parking.core.services.exceptions.*;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private AccountGroupRepo accountGroupRepo;

    @Autowired
    private ConnectionRepo connectionRepo;

    @Autowired
    private ParkingRepo parkingRepo;

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
    public Connection createConnection(Long initiatorId, Long receiverId, Connection data){

        Connection connection = connectionRepo.findByInitiatorReceiver(initiatorId, receiverId);
        if (connection != null) {
            throw new ConnectionExistsException();
        }

        Account initiator = accountRepo.findAccount(initiatorId);
        if (initiator == null) {
            throw new AccountDoesNotExistException();
        }

        Account receiver = accountRepo.findAccount(receiverId);
        if (receiver == null) {
            throw new AccountDoesNotExistException();
        }

        data.setInitiator(initiator);
        data.setReceiver(receiver);

        Connection createdBlog = connectionRepo.createConnection(data);

        return createdBlog;
    }

    @Override
    public Parking createParking(Long accountId, Parking data){
        Account account = accountRepo.findAccount(accountId);
        if (account == null) throw new AccountDoesNotExistException();
        try {
            data = parkingRepo.createParking(data);
        } catch (Exception e) {
            e.printStackTrace();
            throw new GroupExistsException();
        }
        return data;
    }


    @Override
    public AccountGroup createAccountGroup(Long accountId, AccountGroup accountGroup) {

        Account account = accountRepo.findAccount(accountId);
        if (account == null) throw new AccountDoesNotExistException();

        accountGroup.addAccount(account);

        AccountGroup group = null;
        try {
            group = accountGroupRepo.createAccountGroup(accountGroup);
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
    public ParkingList findParkingsByAccount(Long accountId) {
        Parking parking = parkingRepo.findParking(accountId);
        if (parking == null) {
            throw new ParkingDoesNotExistException();
        }
        return new ParkingList(parkingRepo.findParkingsByAccount(accountId));
    }

    @Override
    public ConnectionList findConnectionsByAccount(Long accountId) {
        Connection parking = connectionRepo.findConnection(accountId);
        if (parking == null) {
            throw new ConnectionDoesNotExistException();
        }
        return new ConnectionList(connectionRepo.findConnectionsByAccountId(accountId));
    }

    @Override
    public AccountGroupList findAccountGroupsByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        return new AccountGroupList(account.getAccountGroups());
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
