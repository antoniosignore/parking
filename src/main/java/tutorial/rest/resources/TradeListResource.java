package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class TradeListResource extends ResourceSupport {

    private List<TradeResource> trades = new ArrayList<TradeResource>();

    public List<TradeResource> getTrades() {
        return trades;
    }

    public void setTrades(List<TradeResource> trades) {
        this.trades = trades;
    }
}
