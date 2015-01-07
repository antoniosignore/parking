package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.dtmc.PortfolioEntry;
import tutorial.core.repositories.PortfolioEntryRepo;
import tutorial.core.repositories.TradeRepo;
import tutorial.core.services.PortfolioEntryService;
import tutorial.core.services.exceptions.PortfolioNotFoundException;
import tutorial.core.services.util.TradeList;

@Service
@Transactional
public class PortfolioEntryServiceImpl implements PortfolioEntryService {

    @Autowired
    private PortfolioEntryRepo entryRepo;

    @Autowired
    private TradeRepo tradeRepo;

    @Override
    public PortfolioEntry findPortfolioEntry(Long id) {
        return entryRepo.findPortfolioEntry(id);
    }

    @Override
    public PortfolioEntry deletePortfolioEntry(Long id) {
        return entryRepo.deletePortfolioEntry(id);
    }

    @Override
    public PortfolioEntry updatePortfolioEntry(Long id, PortfolioEntry data) {
        return entryRepo.updatePortfolioEntry(id, data);
    }

    @Override
    public TradeList findAllTrades(Long portfolioEntryId) {
        PortfolioEntry portfolioEntry = entryRepo.findPortfolioEntry(portfolioEntryId);
        if (portfolioEntry == null) {
            throw new PortfolioNotFoundException();
        }
        return new TradeList(portfolioEntryId, tradeRepo.findByPortfolioEntryId(portfolioEntryId));
    }

}
