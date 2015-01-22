package com.rest.resources.asm;

import com.parking.core.services.util.VehicleList;
import com.rest.mvc.AccountController;
import com.rest.mvc.BlogController;
import com.rest.mvc.VehicleController;
import com.rest.resources.VehicleListResource;
import com.rest.resources.VehicleResource;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class VehicleListResourceAsm extends ResourceAssemblerSupport<VehicleList, VehicleListResource> {

    public VehicleListResourceAsm() {
        super(VehicleController.class, VehicleListResource.class);
    }

    @Override
    public VehicleListResource toResource(VehicleList vehicleList) {
        List<VehicleResource> resList = new VehicleResourceAsm().toResources(vehicleList.getVehicles());
        VehicleListResource finalRes = new VehicleListResource();
        finalRes.setVehicles(resList);
//        resList.add(linkTo(methodOn(AccountController.class).findAllAccounts(vehicleList.getVehicles().withSelfRel());

        return finalRes;
    }
}
