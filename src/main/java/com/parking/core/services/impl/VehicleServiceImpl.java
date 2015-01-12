package com.parking.core.services.impl;

import com.parking.core.models.entities.Vehicle;
import com.parking.core.repositories.VehicleRepo;
import com.parking.core.services.VehicleService;
import com.parking.core.services.util.VehicleList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Override
    public VehicleList findAllVehicles() {
        return new VehicleList(vehicleRepo.findAllVehicles());
    }

    @Override
    public Vehicle findVehicle(Long id) {
        return vehicleRepo.findVehicle(id);
    }

    @Override
    public List<Vehicle> findByAccountName(Long name) {
        return vehicleRepo.findVehiclesByAccount(name);
    }
}
