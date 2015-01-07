package tutorial.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.dtmc.Trade;
import tutorial.rest.mvc.PortfolioController;
import tutorial.rest.mvc.StockController;
import tutorial.rest.mvc.TradeController;
import tutorial.rest.resources.TradeResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class TradeResourceAsm extends ResourceAssemblerSupport<Trade, TradeResource> {

    public TradeResourceAsm() {
        super(TradeController.class, TradeResource.class);
    }

    @Override
    public TradeResource toResource(Trade trade) {
        TradeResource res = new TradeResource();
        res.setAmount(trade.getAmount());
        res.setRid(trade.getId());
        Link self = linkTo(TradeController.class).slash(trade.getId()).withSelfRel();
        res.add(self);
        if (trade.getPortfolioEntry() != null) {
            res.add((linkTo(StockController.class).slash(trade.getPortfolioEntry().getId()).withRel("portfolioEntry")));
        }
        return res;
    }
}
