package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tutorial.core.models.entities.dtmc.Stock;
import tutorial.core.services.StockService;
import tutorial.core.services.exceptions.StockNotFoundException;
import tutorial.core.services.util.StockList;
import tutorial.rest.exceptions.NotFoundException;
import tutorial.rest.resources.StockListResource;
import tutorial.rest.resources.StockResource;
import tutorial.rest.resources.asm.StockListResourceAsm;
import tutorial.rest.resources.asm.StockResourceAsm;

import java.net.URI;

@Controller
@RequestMapping("/rest/stocks")
public class StockController {
    private StockService stockService;

    @Autowired
    public StockController(StockService stockService) {
        this.stockService = stockService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<StockListResource> findAllStocks() {
        StockList stockList = stockService.findAllStocks();
        StockListResource stockListRes = new StockListResourceAsm().toResource(stockList);
        return new ResponseEntity<StockListResource>(stockListRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{stockId}",
            method = RequestMethod.GET)
    public ResponseEntity<StockResource> getStock(@PathVariable Long stockId) {
        Stock stock = stockService.findStock(stockId);
        if (stock != null) {
            StockResource res = new StockResourceAsm().toResource(stock);
            return new ResponseEntity<StockResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<StockResource>(HttpStatus.NOT_FOUND);
        }
    }


}
