package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.models.entities.dtmc.Portfolio;
import tutorial.rest.mvc.AccountController;
import tutorial.rest.mvc.PortfolioController;
import tutorial.rest.resources.PortfolioResource;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;

public class PortfolioResourceAsm extends ResourceAssemblerSupport<Portfolio, PortfolioResource> {
    public PortfolioResourceAsm() {
        super(PortfolioController.class, PortfolioResource.class);
    }

    @Override
    public PortfolioResource toResource(Portfolio portfolio) {
        PortfolioResource resource = new PortfolioResource();
        resource.setName(portfolio.getName());
        resource.add(linkTo(PortfolioController.class).slash(portfolio.getId()).withSelfRel());
        resource.setRid(portfolio.getId());
        if (portfolio.getOwner() != null)
            resource.add(linkTo(AccountController.class).slash(portfolio.getOwner().getId()).withRel("owner"));
        return resource;
    }
}
