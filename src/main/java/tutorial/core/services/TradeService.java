package tutorial.core.services;


import tutorial.core.models.entities.dtmc.Trade;

public interface TradeService {

    public Trade findTrade(Long id);

    public Trade deleteTrade(Long id);

    public Trade updateTrade(Long id, Trade data);

}

