package com.parking.core.services;

import com.parking.core.models.entities.Connection;
import com.parking.core.services.util.ConnectionList;

public interface ConnectionService {

    public ConnectionList findAllConnections();

    public Connection findConnection(Long id);

}
