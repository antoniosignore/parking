package tutorial.rest.mvc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import tutorial.core.models.entities.dtmc.PortfolioEntry;
import tutorial.core.services.PortfolioEntryService;
import tutorial.core.services.exceptions.PortfolioNotFoundException;
import tutorial.core.services.util.PortfolioEntryList;
import tutorial.core.services.util.TradeList;
import tutorial.rest.exceptions.NotFoundException;
import tutorial.rest.resources.PortfolioEntryListResource;
import tutorial.rest.resources.PortfolioEntryResource;
import tutorial.rest.resources.TradeListResource;
import tutorial.rest.resources.asm.PortfolioEntryListResourceAsm;
import tutorial.rest.resources.asm.PortfolioEntryResourceAsm;
import tutorial.rest.resources.asm.TradeListResourceAsm;

@Controller
@RequestMapping("/rest/blog-entries")
public class PortfolioEntryController {

    private PortfolioEntryService service;

    @Autowired
    public PortfolioEntryController(PortfolioEntryService service) {
        this.service = service;
    }

    @RequestMapping(value = "/{portfolioEntryId}",
            method = RequestMethod.GET)
    public ResponseEntity<PortfolioEntryResource> getPortfolioEntry(
            @PathVariable Long portfolioEntryId) {
        PortfolioEntry entry = service.findPortfolioEntry(portfolioEntryId);
        if (entry != null) {
            PortfolioEntryResource res = new PortfolioEntryResourceAsm().toResource(entry);
            return new ResponseEntity<PortfolioEntryResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<PortfolioEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{portfolioEntryId}",
            method = RequestMethod.DELETE)
    public ResponseEntity<PortfolioEntryResource> deletePortfolioEntry(
            @PathVariable Long portfolioEntryId) {
        PortfolioEntry entry = service.deletePortfolioEntry(portfolioEntryId);
        if (entry != null) {
            PortfolioEntryResource res = new PortfolioEntryResourceAsm().toResource(entry);
            return new ResponseEntity<PortfolioEntryResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<PortfolioEntryResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{portfolioEntryId}",
            method = RequestMethod.PUT)
    public ResponseEntity<PortfolioEntryResource> updatePortfolioEntry(
            @PathVariable Long portfolioEntryId, @RequestBody PortfolioEntryResource sentPortfolioEntry) {
        PortfolioEntry updatedEntry = service.updatePortfolioEntry(portfolioEntryId, sentPortfolioEntry.toPortfolioEntry());
        if (updatedEntry != null) {
            PortfolioEntryResource res = new PortfolioEntryResourceAsm().toResource(updatedEntry);
            return new ResponseEntity<PortfolioEntryResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<PortfolioEntryResource>(HttpStatus.NOT_FOUND);
        }
    }


    @RequestMapping(value = "/{portfolioEntryId}/trades")
    public ResponseEntity<TradeListResource> findAllTrades(
            @PathVariable Long portfolioEntryId) {
        try {
            TradeList list = service.findAllTrades(portfolioEntryId);
            TradeListResource res = new TradeListResourceAsm().toResource(list);
            return new ResponseEntity<TradeListResource>(res, HttpStatus.OK);
        } catch (PortfolioNotFoundException exception) {
            throw new NotFoundException(exception);
        }
    }
}
