package com.parking.core.services;

import com.parking.core.models.entities.Connection;
import com.parking.core.services.util.ConnectionList;

import java.util.List;

public interface ConnectionService {

    public ConnectionList findAllConnections();

    public Connection findConnection(Long id);

    public List<Connection> findConnectionsByAccountName(String name);

    public List<Connection> findConnectionsByAccountId(Long id);
}
