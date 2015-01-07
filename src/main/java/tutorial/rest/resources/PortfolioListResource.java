package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class PortfolioListResource extends ResourceSupport {

    private List<PortfolioResource> portfolios = new ArrayList<PortfolioResource>();

    public List<PortfolioResource> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(List<PortfolioResource> portfolios) {
        this.portfolios = portfolios;
    }
}
