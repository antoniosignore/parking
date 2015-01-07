package tutorial.core.services;

import tutorial.core.models.entities.dtmc.Portfolio;
import tutorial.core.models.entities.dtmc.PortfolioEntry;
import tutorial.core.services.util.PortfolioEntryList;
import tutorial.core.services.util.PortfolioList;

public interface PortfolioService {

    public PortfolioEntry createPortfolioEntry(Long portfolioId, PortfolioEntry data);

    public PortfolioList findAllPortfolios();

    public PortfolioList findAllPortfoliosByAccount(Long accountId);

    public PortfolioEntryList findAllPortfolioEntries(Long portfolioId);

    public Portfolio findPortfolio(Long id);

}
