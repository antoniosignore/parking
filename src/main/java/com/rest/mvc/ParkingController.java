package com.rest.mvc;

import com.parking.core.models.entities.Parking;
import com.parking.core.services.ParkingService;
import com.parking.core.services.util.ParkingList;
import com.rest.resources.ParkingListResource;
import com.rest.resources.ParkingResource;
import com.rest.resources.asm.ParkingListResourceAsm;
import com.rest.resources.asm.ParkingResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rest/parkings")
public class ParkingController {
    private ParkingService parkingService;

    @Autowired
    public ParkingController(ParkingService parkingService) {
        this.parkingService = parkingService;
    }

    @RequestMapping(method = RequestMethod.GET) @PreAuthorize("permitAll")
    public ResponseEntity<ParkingListResource> findAllParkings() {
        ParkingList parkingList = parkingService.findAllParkings();
        ParkingListResource parkingListResource = new ParkingListResourceAsm().toResource(parkingList);
        return new ResponseEntity<ParkingListResource>(parkingListResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{parkingId}", method = RequestMethod.GET)
    public ResponseEntity<ParkingResource> getParking(@PathVariable Long parkingId) {
        Parking parking = parkingService.findParking(parkingId);
        if (parking != null) {
            ParkingResource res = new ParkingResourceAsm().toResource(parking);
            return new ResponseEntity<ParkingResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<ParkingResource>(HttpStatus.NOT_FOUND);
        }
    }


}
