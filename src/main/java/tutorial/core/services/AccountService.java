package tutorial.core.services;

import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.Blog;
import tutorial.core.models.entities.dtmc.Portfolio;
import tutorial.core.models.entities.dtmc.Stock;
import tutorial.core.models.entities.dtmc.Trade;
import tutorial.core.services.util.AccountList;
import tutorial.core.services.util.BlogList;
import tutorial.core.services.util.PortfolioList;
import tutorial.core.services.util.StockList;

public interface AccountService {
    public Account findAccount(Long id);

    public Account createAccount(Account data);

    public Blog createBlog(Long accountId, Blog data);

    public Portfolio createPortfolio(Long accountId, Portfolio data);

    public Stock createStock(Long accountId, Stock data);

    public BlogList findBlogsByAccount(Long accountId);

    public PortfolioList findPortfoliosByAccount(Long accountId);

    public StockList findStocksByAccount(Long accountId);

    public AccountList findAllAccounts();

    public Account findByAccountName(String name);

    public Trade createTrade(Long accountId, Stock stock, Portfolio portfolio, Trade trade);

}
