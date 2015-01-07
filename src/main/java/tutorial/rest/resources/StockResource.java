package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.dtmc.Stock;

public class StockResource extends ResourceSupport {

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

    public Stock toStock() {
        Stock stock = new Stock();
        stock.setName(name);
        stock.setDescription(description);
        return stock;
    }
}
