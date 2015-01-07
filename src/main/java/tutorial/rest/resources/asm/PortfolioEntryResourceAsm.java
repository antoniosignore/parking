package tutorial.rest.resources.asm;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.dtmc.PortfolioEntry;
import tutorial.rest.mvc.PortfolioController;
import tutorial.rest.mvc.PortfolioEntryController;
import tutorial.rest.mvc.StockController;
import tutorial.rest.resources.PortfolioEntryResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class PortfolioEntryResourceAsm extends ResourceAssemblerSupport<PortfolioEntry, PortfolioEntryResource> {

    public PortfolioEntryResourceAsm() {
        super(PortfolioEntryController.class, PortfolioEntryResource.class);
    }

    @Override
    public PortfolioEntryResource toResource(PortfolioEntry portfolioEntry) {
        PortfolioEntryResource res = new PortfolioEntryResource();
        res.setAmount(portfolioEntry.getAmount());
        res.setRid(portfolioEntry.getId());
        Link self = linkTo(PortfolioEntryController.class).slash(portfolioEntry.getId()).withSelfRel();
        res.add(self);
        if (portfolioEntry.getInstrument() != null) {
            res.add((linkTo(StockController.class).slash(portfolioEntry.getInstrument().getId()).withRel("instrument")));
        }
        if (portfolioEntry.getPortfolio() != null) {
            res.add((linkTo(PortfolioController.class).slash(portfolioEntry.getPortfolio().getId()).withRel("portfolio")));
        }
        return res;
    }
}
