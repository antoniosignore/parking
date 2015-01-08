package com.parking.core.services;

import com.parking.core.models.entities.Account;
import com.parking.core.models.entities.Blog;
import com.parking.core.models.entities.dtmc.Portfolio;
import com.parking.core.models.entities.dtmc.Stock;
import com.parking.core.models.entities.dtmc.Trade;
import com.parking.core.services.util.AccountList;
import com.parking.core.services.util.BlogList;
import com.parking.core.services.util.PortfolioList;
import com.parking.core.services.util.GroupList;

public interface AccountService {
    public Account findAccount(Long id);

    public Account createAccount(Account data);

    public Blog createBlog(Long accountId, Blog data);

    public Portfolio createPortfolio(Long accountId, Portfolio data);

    public Stock createStock(Long accountId, Stock data);

    public BlogList findBlogsByAccount(Long accountId);

    public PortfolioList findPortfoliosByAccount(Long accountId);

    public GroupList findStocksByAccount(Long accountId);

    public AccountList findAllAccounts();

    public Account findByAccountName(String name);

    public Trade createTrade(Long accountId, Stock stock, Portfolio portfolio, Trade trade);

}
