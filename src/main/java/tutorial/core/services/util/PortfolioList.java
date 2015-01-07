package tutorial.core.services.util;


import tutorial.core.models.entities.dtmc.Portfolio;

import java.util.ArrayList;
import java.util.List;

public class PortfolioList {

    private List<Portfolio> portfolios = new ArrayList<Portfolio>();

    public PortfolioList(List<Portfolio> resultList) {
        this.portfolios = resultList;
    }

    public List<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }
}
