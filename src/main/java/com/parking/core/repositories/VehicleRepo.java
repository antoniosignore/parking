package com.parking.core.repositories;

import com.parking.core.models.entities.Vehicle;

import java.util.List;

public interface VehicleRepo {

    public Vehicle createVehicle(Vehicle data);

    public List<Vehicle> findAllVehicles();

    public Vehicle findVehicle(Long id);

    public List<Vehicle> findVehiclesByAccountId(Long accountId);

    public Vehicle findVehicleByName(String name);

}
