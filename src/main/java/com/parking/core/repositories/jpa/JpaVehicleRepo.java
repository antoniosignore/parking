package com.parking.core.repositories.jpa;

import com.parking.core.models.entities.Vehicle;
import com.parking.core.repositories.VehicleRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaVehicleRepo implements VehicleRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Vehicle createVehicle(Vehicle data) {
        em.persist(data);
        em.flush();
        return data;
    }

    @Override
    public List<Vehicle> findAllVehicles() {
        Query query = em.createQuery("SELECT b from Vehicle b");
        return query.getResultList();
    }

    @Override
    public Vehicle findVehicle(Long id) {
        return em.find(Vehicle.class, id);
    }

    @Override
    public Vehicle findVehicleByName(String title) {
        Query query = em.createQuery("SELECT b from Vehicle b where b.title=?1");
        query.setParameter(1, title);
        List<Vehicle> vehicles = query.getResultList();
        if (vehicles.isEmpty()) {
            return null;
        } else {
            return vehicles.get(0);
        }
    }

    @Override
    public List<Vehicle> findVehiclesByAccount(Long accountId) {
        Query query = em.createQuery("SELECT b from Vehicle b where b.owner.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }

}
