package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.dtmc.Trade;
import tutorial.core.repositories.TradeRepo;
import tutorial.core.services.TradeService;

@Service
@Transactional
public class TradeServiceImpl implements TradeService {

    @Autowired
    private TradeRepo tradeRepo;

    @Override
    public Trade findTrade(Long id) {
        return tradeRepo.findTrade(id);
    }

    @Override
    public Trade deleteTrade(Long id) {
        return tradeRepo.deleteTrade(id);
    }

    @Override
    public Trade updateTrade(Long id, Trade data) {
        return tradeRepo.updateTrade(id, data);
    }
}
