package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class StockListResource extends ResourceSupport {

    private List<StockResource> stocks = new ArrayList<StockResource>();

    public List<StockResource> getStocks() {
        return stocks;
    }

    public void setStocks(List<StockResource> stocks) {
        this.stocks = stocks;
    }
}
