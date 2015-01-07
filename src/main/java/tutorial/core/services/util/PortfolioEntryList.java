package tutorial.core.services.util;


import tutorial.core.models.entities.dtmc.PortfolioEntry;

import java.util.ArrayList;
import java.util.List;

public class PortfolioEntryList {

    private List<PortfolioEntry> entries = new ArrayList<PortfolioEntry>();
    private Long portfolioId;

    public PortfolioEntryList(Long portfolioId, List<PortfolioEntry> entries) {
        this.portfolioId = portfolioId;
        this.entries = entries;
    }

    public List<PortfolioEntry> getEntries() {
        return entries;
    }

    public void setEntries(List<PortfolioEntry> entries) {
        this.entries = entries;
    }

    public Long getPortfolioId() {
        return portfolioId;
    }

    public void setPortfolioId(Long portfolioId) {
        this.portfolioId = portfolioId;
    }
}
