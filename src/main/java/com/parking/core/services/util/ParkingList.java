package com.parking.core.services.util;

import com.parking.core.models.entities.Parking;

import java.util.ArrayList;
import java.util.List;

public class ParkingList {

    private List<Parking> parkings = new ArrayList<Parking>();

    public ParkingList(List resultList) {
        this.parkings = resultList;
    }

    public List<Parking> getParkings() {
        return parkings;
    }

    public void setParkings(List<Parking> parkings) {
        this.parkings = parkings;
    }
}
