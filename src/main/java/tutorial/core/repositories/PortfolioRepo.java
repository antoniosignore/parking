package tutorial.core.repositories;

import tutorial.core.models.entities.dtmc.Portfolio;

import java.util.List;


public interface PortfolioRepo {

    public Portfolio createPortfolio(Portfolio data);

    public List<Portfolio> findAllPortfolios();

    public Portfolio findPortfolio(Long id);

    public List<Portfolio> findPortfoliosByAccount(Long accountId);

    Portfolio findPortfoliosByAccountAndName(Long accountId, String name);
}
