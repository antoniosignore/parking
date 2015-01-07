package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.dtmc.Portfolio;
import tutorial.core.models.entities.dtmc.Stock;
import tutorial.core.models.entities.dtmc.Trade;
import tutorial.core.repositories.*;
import tutorial.core.services.AccountService;
import tutorial.core.services.exceptions.*;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;
import tutorial.core.services.util.PortfolioList;
import tutorial.core.services.util.StockList;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private BlogRepo blogRepo;

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private StockRepo stockRepo;

    @Autowired
    private TradeRepo tradeRepo;

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
    public Portfolio createPortfolio(Long accountId, Portfolio data) {

        // todo = hibernate can pick up the duplication in the create....
        Portfolio portfolio = portfolioRepo.findPortfoliosByAccountAndName(accountId, data.getName());

        if (portfolio != null) throw new PortfolioExistsException();

        Account account = accountRepo.findAccount(accountId);
        if (account == null) throw new AccountDoesNotExistException();

        data.setOwner(account);
        return portfolioRepo.createPortfolio(data);
    }


    @Override
    public Stock createStock(Long accountId, Stock stock) {
        Stock portfolioSameTitle = stockRepo.findStockByAccountAndTicker(accountId, stock.getName());

        if (portfolioSameTitle != null) throw new StockExistsException();

        Account account = accountRepo.findAccount(accountId);
        if (account == null) throw new AccountDoesNotExistException();

        stock.setOwner(account);

        return stockRepo.createStock(stock);
    }

    @Override
    public Trade createTrade(Long accountId, Stock stock, Portfolio portfolio, Trade trade) {

        Account account = accountRepo.findAccount(accountId);
        if (account == null) throw new AccountDoesNotExistException();

        Portfolio p = portfolioRepo.findPortfoliosByAccountAndName(accountId, stock.getName());
        if (p != null) throw new PortfolioExistsException();

        Stock s = stockRepo.findStockByAccountAndTicker(accountId, stock.getName());
        if (s != null) throw new StockExistsException();

        // todo find the portfolio entry
        trade.setPortfolioEntry(null);

        return tradeRepo.createTrade(trade);
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
    public PortfolioList findPortfoliosByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        return new PortfolioList(portfolioRepo.findPortfoliosByAccount(accountId));
    }

    @Override
    public StockList findStocksByAccount(Long accountId) {
        Account account = accountRepo.findAccount(accountId);
        if (account == null) {
            throw new AccountDoesNotExistException();
        }
        return new StockList(stockRepo.findStocksByAccount(accountId));
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
