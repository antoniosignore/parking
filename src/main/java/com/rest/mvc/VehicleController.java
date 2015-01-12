package com.rest.mvc;

import com.parking.core.models.entities.Vehicle;
import com.parking.core.services.VehicleService;
import com.parking.core.services.util.VehicleList;
import com.rest.resources.VehicleListResource;
import com.rest.resources.VehicleResource;
import com.rest.resources.asm.VehicleListResourceAsm;
import com.rest.resources.asm.VehicleResourceAsm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/rest/vehicles")
public class VehicleController {
    private VehicleService vehicleService;

    @Autowired
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @RequestMapping(method = RequestMethod.GET) @PreAuthorize("permitAll")
    public ResponseEntity<VehicleListResource> findAllVehicles() {
        VehicleList vehicleList = vehicleService.findAllVehicles();
        VehicleListResource vehicleListResource = new VehicleListResourceAsm().toResource(vehicleList);
        return new ResponseEntity<VehicleListResource>(vehicleListResource, HttpStatus.OK);
    }

    @RequestMapping(value = "/{vehicleId}", method = RequestMethod.GET)
    public ResponseEntity<VehicleResource> getVehicle(@PathVariable Long vehicleId) {
        Vehicle vehicle = vehicleService.findVehicle(vehicleId);
        if (vehicle != null) {
            VehicleResource res = new VehicleResourceAsm().toResource(vehicle);
            return new ResponseEntity<VehicleResource>(res, HttpStatus.OK);
        } else {
            return new ResponseEntity<VehicleResource>(HttpStatus.NOT_FOUND);
        }
    }


}
