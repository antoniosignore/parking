package tutorial.rest.resources.asm;

import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import tutorial.core.services.util.StockList;
import tutorial.rest.mvc.StockController;
import tutorial.rest.resources.StockListResource;


public class StockListResourceAsm extends ResourceAssemblerSupport<StockList, StockListResource> {

    public StockListResourceAsm() {
        super(StockController.class, StockListResource.class);
    }

    @Override
    public StockListResource toResource(StockList stockList) {
        StockListResource res = new StockListResource();
        res.setStocks(new StockResourceAsm().toResources(stockList.getStocks()));
        return res;
    }
}
