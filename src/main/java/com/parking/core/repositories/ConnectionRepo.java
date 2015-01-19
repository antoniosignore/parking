package com.parking.core.repositories;

import com.parking.core.models.entities.Connection;

import java.util.List;

public interface ConnectionRepo {

    public Connection createConnection(Connection data);

    public List<Connection> findAllConnections();

    public Connection findConnection(Long id);

    public List<Connection> findConnectionsByAccountName(String name);

    public Connection findByInitiatorReceiver(Long initiatorId, Long receiverId);

    public List<Connection> findConnectionsByAccountId(Long accountId);

    public Connection findConnectionByAccountNames(String initiatorName, String receiverName);
}
