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
import tutorial.core.models.entities.dtmc.Portfolio;
import tutorial.core.models.entities.dtmc.PortfolioEntry;
import tutorial.core.services.PortfolioService;
import tutorial.core.services.exceptions.PortfolioNotFoundException;
import tutorial.core.services.util.PortfolioEntryList;
import tutorial.core.services.util.PortfolioList;
import tutorial.rest.exceptions.NotFoundException;
import tutorial.rest.resources.PortfolioEntryListResource;
import tutorial.rest.resources.PortfolioEntryResource;
import tutorial.rest.resources.PortfolioListResource;
import tutorial.rest.resources.PortfolioResource;
import tutorial.rest.resources.asm.PortfolioEntryListResourceAsm;
import tutorial.rest.resources.asm.PortfolioEntryResourceAsm;
import tutorial.rest.resources.asm.PortfolioListResourceAsm;
import tutorial.rest.resources.asm.PortfolioResourceAsm;

import java.net.URI;

@Controller
@RequestMapping("/rest/portfolios")
public class PortfolioController {
    private PortfolioService portfolioService;

    @Autowired
    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @RequestMapping(method = RequestMethod.GET)
    @PreAuthorize("permitAll")
    public ResponseEntity<PortfolioListResource> findAllPortfolios() {
        PortfolioList portfolioList = portfolioService.findAllPortfolios();
        PortfolioListResource portfolioListRes = new PortfolioListResourceAsm().toResource(portfolioList);
        return new ResponseEntity<PortfolioListResource>(portfolioListRes, HttpStatus.OK);
    }

    @RequestMapping(value = "/{portfolioId}",
            method = RequestMethod.GET)
    public ResponseEntity<PortfolioResource> getPortfolio(@PathVariable Long portfolioId) {
        Portfolio portfolio = portfolioService.findPortfolio(portfolioId);
        if (portfolio != null) {
            PortfolioResource res = new PortfolioResourceAsm().toResource(portfolio);
            return new ResponseEntity<PortfolioResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<PortfolioResource>(HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/{portfolioId}/portfolio-entries",
            method = RequestMethod.POST)
    public ResponseEntity<PortfolioEntryResource> createPortfolioEntry(
            @PathVariable Long portfolioId,
            @RequestBody PortfolioEntryResource sentPortfolioEntry
    ) {
        PortfolioEntry createdPortfolioEntry = null;
        try {
            createdPortfolioEntry = portfolioService.createPortfolioEntry(portfolioId, sentPortfolioEntry.toPortfolioEntry());
            PortfolioEntryResource createdResource = new PortfolioEntryResourceAsm().toResource(createdPortfolioEntry);
            HttpHeaders headers = new HttpHeaders();
            headers.setLocation(URI.create(createdResource.getLink("self").getHref()));
            return new ResponseEntity<PortfolioEntryResource>(createdResource, headers, HttpStatus.CREATED);
        } catch (PortfolioNotFoundException e) {
            throw new NotFoundException(e);
        }
    }

    @RequestMapping(value = "/{portfolioId}/portfolio-entries")
    public ResponseEntity<PortfolioEntryListResource> findAllPortfolioEntries(
            @PathVariable Long portfolioId) {
        try {
            PortfolioEntryList list = portfolioService.findAllPortfolioEntries(portfolioId);
            PortfolioEntryListResource res = new PortfolioEntryListResourceAsm().toResource(list);
            return new ResponseEntity<PortfolioEntryListResource>(res, HttpStatus.OK);
        } catch (PortfolioNotFoundException exception) {
            throw new NotFoundException(exception);
        }
    }

}
