package com.parking.core.repositories;

import com.parking.core.models.entities.Connection;

import java.util.List;

public interface ConnectionRepo {

    public List<Connection> findAllConnections();

    public Connection findConnection(Long id);

    public Connection createConnection(Connection data);
    
    
}
