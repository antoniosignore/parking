package com.parking.core.services;

import com.parking.core.models.entities.Account;
import com.parking.core.models.entities.Blog;
import com.parking.core.models.entities.AccountGroup;
import com.parking.core.services.util.AccountList;
import com.parking.core.services.util.BlogList;
import com.parking.core.services.util.GroupList;

public interface AccountService {

    public Account findAccount(Long id);

    public Account createAccount(Account data);

    public Blog createBlog(Long accountId, Blog data);

    public AccountGroup createGroup(Long accountId, AccountGroup data);

    public BlogList findBlogsByAccount(Long accountId);

    public GroupList findGroupsByAccount(Long accountId);

    public AccountList findAllAccounts();

    public Account findByAccountName(String name);

}
