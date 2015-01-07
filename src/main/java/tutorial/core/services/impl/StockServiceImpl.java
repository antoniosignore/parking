package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.dtmc.Stock;
import tutorial.core.repositories.StockRepo;
import tutorial.core.services.StockService;
import tutorial.core.services.util.StockList;

@Service
@Transactional
public class StockServiceImpl implements StockService {

    @Autowired
    private StockRepo stockRepo;

    @Override
    public StockList findAllStocks() {
        return new StockList(stockRepo.findAllStocks());
    }

    @Override
    public StockList findAllStocksByAccount(Long accountId) {
        return new StockList(stockRepo.findStocksByAccount(accountId));
    }

    @Override
    public Stock findStock(Long id) {
        return stockRepo.findStock(id);
    }
}
