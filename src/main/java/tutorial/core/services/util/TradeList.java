package tutorial.core.services.util;


import tutorial.core.models.entities.dtmc.Trade;

import java.util.ArrayList;
import java.util.List;

public class TradeList {

    private List<Trade> entries = new ArrayList<Trade>();
    private Long portfolioEntryId;

    public TradeList(Long portfolioEntryId, List<Trade> entries) {
        this.portfolioEntryId = portfolioEntryId;
        this.entries = entries;
    }

    public List<Trade> getEntries() {
        return entries;
    }

    public void setEntries(List<Trade> entries) {
        this.entries = entries;
    }

    public Long getPortfolioEntryId() {
        return portfolioEntryId;
    }

    public void setPortfolioEntryId(Long portfolioEntryId) {
        this.portfolioEntryId = portfolioEntryId;
    }
}
