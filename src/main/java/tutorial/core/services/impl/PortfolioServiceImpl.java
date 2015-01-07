package tutorial.core.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.dtmc.Portfolio;
import tutorial.core.models.entities.dtmc.PortfolioEntry;
import tutorial.core.repositories.PortfolioEntryRepo;
import tutorial.core.repositories.PortfolioRepo;
import tutorial.core.services.PortfolioService;
import tutorial.core.services.exceptions.PortfolioNotFoundException;
import tutorial.core.services.util.PortfolioEntryList;
import tutorial.core.services.util.PortfolioList;

@Service
@Transactional
public class PortfolioServiceImpl implements PortfolioService {

    @Autowired
    private PortfolioRepo portfolioRepo;

    @Autowired
    private PortfolioEntryRepo entryRepo;

    @Override
    public PortfolioEntry createPortfolioEntry(Long portfolioId, PortfolioEntry data) {
        Portfolio portfolio = portfolioRepo.findPortfolio(portfolioId);
        if (portfolio == null) {
            throw new PortfolioNotFoundException();
        }

        data.setPortfolio(portfolio);
        return entryRepo.createPortfolioEntry(data);
    }

    @Override
    public PortfolioList findAllPortfolios() {
        return new PortfolioList(portfolioRepo.findAllPortfolios());
    }

    @Override
    public PortfolioList findAllPortfoliosByAccount(Long accountId) {
        return new PortfolioList(portfolioRepo.findPortfoliosByAccount(accountId));
    }

    @Override
    public PortfolioEntryList findAllPortfolioEntries(Long portfolioId) {
        Portfolio portfolio = portfolioRepo.findPortfolio(portfolioId);
        if (portfolio == null) {
            throw new PortfolioNotFoundException();
        }
        return new PortfolioEntryList(portfolioId, entryRepo.findByPortfolioId(portfolioId));
    }

    @Override
    public Portfolio findPortfolio(Long portfolioId) {
        return portfolioRepo.findPortfolio(portfolioId);
    }
}
