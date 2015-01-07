package tutorial.core.repositories;

import tutorial.core.models.entities.dtmc.PortfolioEntry;

import java.util.List;

public interface PortfolioEntryRepo {

    public PortfolioEntry findPortfolioEntry(Long id);

    public PortfolioEntry deletePortfolioEntry(Long id);

    public PortfolioEntry updatePortfolioEntry(Long id, PortfolioEntry data);

    public PortfolioEntry createPortfolioEntry(PortfolioEntry data);

    public List<PortfolioEntry> findByPortfolioId(Long portfolioId);
}
