package com.parking.core.services;

import com.parking.core.models.entities.*;
import com.parking.core.services.util.*;

public interface AccountService {

    public Account findAccount(Long id);
    public Account findByAccountName(String name);

    public Account createAccount(Account data);

    public AccountGroup createAccountGroup(Long accountId, AccountGroup data);
    public Blog createBlog(Long accountId, Blog data);
    public Connection createConnection(Long accountId, Long receiverId, Connection data);
    public Parking createParking(Long accountId, Parking data);
    public Vehicle createVehicle(Long accountId, Vehicle data);

    public AccountList findAllAccounts();
    public BlogList findBlogsByAccount(Long accountId);
    public ParkingList findParkingsByAccount(Long accountId);
    public ConnectionList findConnectionsByAccount(Long accountId);
    public AccountGroupList findAccountGroupsByAccount(Long accountId);
    public VehicleList findVehiclesByAccount(Long accountId);
}
