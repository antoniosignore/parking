package com.rest.resources;

import org.springframework.hateoas.ResourceSupport;

import java.util.ArrayList;
import java.util.List;

public class ParkingListResource extends ResourceSupport {

    private List<ConnectionResource> parkings = new ArrayList<ConnectionResource>();

    public List<ConnectionResource> getParkings() {
        return parkings;
    }

    public void setParkings(List<ConnectionResource> parkings) {
        this.parkings = parkings;
    }
}
