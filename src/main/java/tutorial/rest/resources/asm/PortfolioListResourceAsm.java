package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.PortfolioList;
import tutorial.rest.mvc.PortfolioController;
import tutorial.rest.resources.PortfolioListResource;


public class PortfolioListResourceAsm extends ResourceAssemblerSupport<PortfolioList, PortfolioListResource> {

    public PortfolioListResourceAsm() {
        super(PortfolioController.class, PortfolioListResource.class);
    }

    @Override
    public PortfolioListResource toResource(PortfolioList portfolioList) {
        PortfolioListResource res = new PortfolioListResource();
        res.setPortfolios(new PortfolioResourceAsm().toResources(portfolioList.getPortfolios()));
        return res;
    }
}
