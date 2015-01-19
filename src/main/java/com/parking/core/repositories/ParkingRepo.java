package com.parking.core.repositories;

import com.parking.core.models.entities.Parking;

import java.util.List;

public interface ParkingRepo {

    public Parking createParking(Parking data);

    public List<Parking> findAllParkings();

    public Parking findParking(Long id);

    public List<Parking> findParkingsByAccount(Long accountId);

    public List<Parking> findParkingsByAccountName(String name);

}
