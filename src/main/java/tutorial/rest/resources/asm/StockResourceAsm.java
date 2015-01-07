package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.dtmc.Stock;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.mvc.StockController;
import tutorial.rest.resources.StockResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class StockResourceAsm extends ResourceAssemblerSupport<Stock, StockResource> {
    public StockResourceAsm() {
        super(StockController.class, StockResource.class);
    }

    @Override
    public StockResource toResource(Stock stock) {
        StockResource resource = new StockResource();
        resource.setName(stock.getName());
        resource.add(linkTo(StockController.class).slash(stock.getId()).withSelfRel());
        resource.setRid(stock.getId());
        if (stock.getOwner() != null)
            resource.add(linkTo(AccountController.class).slash(stock.getOwner().getId()).withRel("owner"));
        return resource;
    }
}
