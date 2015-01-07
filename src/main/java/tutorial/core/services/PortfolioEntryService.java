package tutorial.core.services;


import tutorial.core.models.entities.dtmc.PortfolioEntry;
import tutorial.core.services.util.TradeList;

public interface PortfolioEntryService {

    public PortfolioEntry findPortfolioEntry(Long id);

    public PortfolioEntry deletePortfolioEntry(Long id);

    public PortfolioEntry updatePortfolioEntry(Long id, PortfolioEntry data);

    public TradeList findAllTrades(Long portfolioEntryId) ;

}
