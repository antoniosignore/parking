package com.parking.core.services;

import com.parking.core.models.entities.Parking;
import com.parking.core.services.util.ParkingList;

import java.util.List;

public interface ParkingService {

    public ParkingList findAllParkings();

    public Parking findParking(Long id);

    public List<Parking> findParkingByAccountId(Long id);

    public List<Parking> findParkingsByAccountName(String name);

//    public AccountGroup deleteParking(Long id);
//
//    public AccountGroup updateParking(Long id, Parking data);


}
