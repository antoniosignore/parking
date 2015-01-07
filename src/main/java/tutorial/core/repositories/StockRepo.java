package tutorial.core.repositories;

import tutorial.core.models.entities.dtmc.Stock;

import java.util.List;


public interface StockRepo {
    public Stock createStock(Stock data);

    public List<Stock> findAllStocks();

    public Stock findStock(Long id);

    public Stock findStockByAccountAndTicker(Long accountId, String title);

    public List<Stock> findStocksByAccount(Long accountId);

}
