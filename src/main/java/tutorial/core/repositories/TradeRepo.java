package tutorial.core.repositories;

import tutorial.core.models.entities.dtmc.Trade;

import java.util.List;

public interface TradeRepo {

    public Trade findTrade(Long id);

    public Trade deleteTrade(Long id);

    public Trade updateTrade(Long id, Trade data);

    public Trade createTrade(Trade data);

    public List<Trade> findByPortfolioEntryId(Long portfolioEntryId);
}
