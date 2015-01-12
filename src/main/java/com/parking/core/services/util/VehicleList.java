package com.parking.core.services.util;

import com.parking.core.models.entities.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class VehicleList {

    private List<Vehicle> vehicles = new ArrayList<Vehicle>();

    public VehicleList(List resultList) {
        this.vehicles = resultList;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void setVehicles(List<Vehicle> vehicles) {
        this.vehicles = vehicles;
    }
}
