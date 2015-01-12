package com.parking.core.repositories;

import com.parking.core.models.entities.Account;
import com.parking.core.models.entities.AccountGroup;
import com.parking.core.models.entities.Vehicle;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class VehicleRepoTest {

    @Autowired
    private VehicleRepo vehicleRepo;

    @Autowired
    private AccountRepo accountRepo;

    private Account antonio;
    private Account mida;

    @Before
    @Transactional
    @Rollback(false)
    public void setup() {

        antonio = new Account();
        antonio.setName("antonio");
        antonio.setPassword("ciccionazzo");

        mida = new Account();
        mida.setName("mida");
        mida.setPassword("provolone");

        accountRepo.createAccount(antonio);
        accountRepo.createAccount(mida);

        createVehicle("D-AX12345", "BMW", antonio);
        createVehicle("D-AX12346", "mercedes", antonio);
        createVehicle("D-AX12347", "ferrari", mida);
    }


    private void createVehicle(String licensePlate, String name, Account account) {
        Vehicle vehicle = new Vehicle();

        vehicle.setLicensePlate(licensePlate);
        vehicle.setName(name);
        vehicle.setOwner(account);

        vehicleRepo.createVehicle(vehicle);
    }

    @Test
    @Transactional
    public void testFindVehicleByName() {

        Vehicle vehicle = vehicleRepo.findVehicleByName("BMW");
        assertNotNull(vehicle);
        assertEquals(vehicle.getName(), "BMW");
        assertEquals(vehicle.getOwner(), antonio);
        assertEquals(vehicle.getLicensePlate(), "D-AX12345");

    }

    @Test
    @Transactional
    public void testFindAllAccounts() {

        List<Vehicle> allVehicles = vehicleRepo.findAllVehicles();
        assertEquals(3, allVehicles.size());

    }

    @Test
    @Transactional
    public void testFindAllVehiclesByAccounts() {

        List<Vehicle> allVehicles = vehicleRepo.findVehiclesByAccount(antonio.getId());
        assertEquals(2, allVehicles.size());

    }

    @Test
    @Transactional
    public void testFindAllVehiclesAccounts() {

         Vehicle vehicle = vehicleRepo.findVehicleByName("BMW");
        assertEquals(vehicle.getName(), "BMW");
        assertEquals(vehicle.getOwner(), antonio);
        assertEquals(vehicle.getLicensePlate(), "D-AX12345");

    }

}
