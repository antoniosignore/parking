package com.parking.core.services;

import com.parking.core.models.entities.Parking;
import com.parking.core.services.util.ParkingList;

public interface ParkingService {

    public ParkingList findAllParkings();

    public Parking findParking(Long id);

}
