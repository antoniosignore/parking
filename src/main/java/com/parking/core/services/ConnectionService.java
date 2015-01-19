package com.parking.core.services;

import com.parking.core.models.entities.Connection;
import com.parking.core.services.util.ConnectionList;

import java.util.List;

public interface ConnectionService {

//    public Connection createConnection(Connection data);

    public ConnectionList findAllConnections();

    public Connection findConnection(Long id);

    List<Connection> findByAccountName(String name);
}
