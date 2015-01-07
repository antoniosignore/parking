package tutorial.core.services;

import tutorial.core.models.entities.dtmc.Stock;
import tutorial.core.services.util.StockList;

public interface StockService {

    public StockList findAllStocks();

    public StockList findAllStocksByAccount(Long accountId);

    public Stock findStock(Long id);

}
