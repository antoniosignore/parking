package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tutorial.core.models.entities.dtmc.Trade;
import tutorial.core.services.TradeService;
import tutorial.rest.resources.TradeResource;
import tutorial.rest.resources.asm.TradeResourceAsm;

@Controller
@RequestMapping("/rest/blog-entries")
public class TradeController {
    private TradeService service;

    @Autowired
    public TradeController(TradeService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{tradeId}",
            method = RequestMethod.GET)
    public ResponseEntity<TradeResource> getTrade(
            @PathVariable Long tradeId) {
        Trade entry = service.findTrade(tradeId);
        if (entry != null) {
            TradeResource res = new TradeResourceAsm().toResource(entry);
            return new ResponseEntity<TradeResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<TradeResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{tradeId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<TradeResource> deleteTrade(
            @PathVariable Long tradeId) {
        Trade entry = service.deleteTrade(tradeId);
        if (entry != null) {
            TradeResource res = new TradeResourceAsm().toResource(entry);
            return new ResponseEntity<TradeResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<TradeResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{tradeId}",
            method = RequestMethod.PUT)
    public ResponseEntity<TradeResource> updateTrade(
            @PathVariable Long tradeId, @RequestBody TradeResource sentTrade) {
        Trade updatedEntry = service.updateTrade(tradeId, sentTrade.toTrade());
        if (updatedEntry != null) {
            TradeResource res = new TradeResourceAsm().toResource(updatedEntry);
            return new ResponseEntity<TradeResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<TradeResource>(HttpStatus.NOT_FOUND);
        }
    }
}
