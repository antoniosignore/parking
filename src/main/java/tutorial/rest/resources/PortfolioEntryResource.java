package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;
import tutorial.core.models.entities.dtmc.PortfolioEntry;

public class PortfolioEntryResource extends ResourceSupport {

    Integer amount;

    private Long rid;

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public PortfolioEntry toPortfolioEntry() {
        PortfolioEntry entry = new PortfolioEntry();
        entry.setAmount(amount);
        return entry;
    }
}
