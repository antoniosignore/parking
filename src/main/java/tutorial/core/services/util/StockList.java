package tutorial.core.services.util;


import tutorial.core.models.entities.dtmc.Stock;

import java.util.ArrayList;
import java.util.List;

public class StockList {

    private List<Stock> stocks = new ArrayList<Stock>();

    public StockList(List<Stock> resultList) {
        this.stocks = resultList;
    }

    public List<Stock> getStocks() {
        return stocks;
    }

    public void setStocks(List<Stock> stocks) {
        this.stocks = stocks;
    }
}
