package tutorial.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.List;

public class PortfolioEntryListResource extends ResourceSupport {
    private List<PortfolioEntryResource> entries;

    public List<PortfolioEntryResource> getEntries() {
        return entries;
    }

    public void setEntries(List<PortfolioEntryResource> entries) {
        this.entries = entries;
    }
}
