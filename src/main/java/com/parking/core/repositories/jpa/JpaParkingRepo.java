package com.parking.core.repositories.jpa;

import com.parking.core.models.entities.Connection;
import com.parking.core.models.entities.Parking;
import com.parking.core.repositories.ParkingRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaParkingRepo implements ParkingRepo {

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Parking> findAllParkings() {
        Query query = em.createQuery("SELECT a FROM Parking a");
        return query.getResultList();
    }

    @Override
    public Parking findParking(Long id) {
        return em.find(Parking.class, id);
    }

    @Override
    public List<Parking> findParkingsByAccount(Long accountId) {
        Query query = em.createQuery("SELECT a FROM Parking a WHERE a.account.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }

    @Override
    public Parking createParking(Parking data) {
        em.persist(data);
        em.flush();
        return data;
    }

    public List<Parking> findParkingsByAccountName(String name){
        Query query = em.createQuery("SELECT a FROM Parking a WHERE a.account.name=?1");
        query.setParameter(1, name);
        return query.getResultList();

    }
}
