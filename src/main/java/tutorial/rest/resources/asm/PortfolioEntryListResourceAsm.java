package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.PortfolioEntryList;
import tutorial.rest.mvc.PortfolioController;
import tutorial.rest.resources.PortfolioEntryListResource;
import tutorial.rest.resources.PortfolioEntryResource;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class PortfolioEntryListResourceAsm extends ResourceAssemblerSupport<PortfolioEntryList, PortfolioEntryListResource> {
    public PortfolioEntryListResourceAsm() {
        super(PortfolioController.class, PortfolioEntryListResource.class);
    }

    @Override
    public PortfolioEntryListResource toResource(PortfolioEntryList list) {
        List<PortfolioEntryResource> resources = new PortfolioEntryResourceAsm().toResources(list.getEntries());
        PortfolioEntryListResource listResource = new PortfolioEntryListResource();
        listResource.setEntries(resources);
        listResource.add(linkTo(methodOn(PortfolioController.class).findAllPortfolioEntries(list.getPortfolioId())).withSelfRel());
        return listResource;
    }
}
