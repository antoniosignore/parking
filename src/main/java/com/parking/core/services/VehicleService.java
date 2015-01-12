package com.parking.core.services;

import com.parking.core.models.entities.Vehicle;
import com.parking.core.services.util.VehicleList;

import java.util.List;

public interface VehicleService {

    public VehicleList findAllVehicles();

    public Vehicle findVehicle(Long id);

    public List<Vehicle> findByAccountName(Long name);

    }
