package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.dtmc.Portfolio;
import tutorial.core.models.entities.dtmc.Stock;

public class PortfolioResource extends ResourceSupport {

    private String name;

    private String description;

    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Portfolio toPortfolio() {
        Portfolio portfolio = new Portfolio();
        portfolio.setName(name);
        return portfolio;
    }
}
