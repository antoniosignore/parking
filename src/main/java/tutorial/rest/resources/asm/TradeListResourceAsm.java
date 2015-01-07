package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.TradeList;
import tutorial.rest.mvc.PortfolioEntryController;
import tutorial.rest.mvc.TradeController;
import tutorial.rest.resources.TradeListResource;
import tutorial.rest.resources.TradeResource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class TradeListResourceAsm extends ResourceAssemblerSupport<TradeList, TradeListResource> {

    public TradeListResourceAsm() {
        super(TradeController.class, TradeListResource.class);
    }

    @Override
    public TradeListResource toResource(TradeList list) {
        List<TradeResource> resources = new TradeResourceAsm().toResources(list.getEntries());
        TradeListResource listResource = new TradeListResource();
        listResource.setTrades(resources);
        listResource.add(linkTo(methodOn(PortfolioEntryController.class).findAllTrades(list.getPortfolioEntryId())).withSelfRel());
        return listResource;
    }
}
