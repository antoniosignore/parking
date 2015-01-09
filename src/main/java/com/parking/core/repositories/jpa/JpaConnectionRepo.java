package com.parking.core.repositories.jpa;

import com.parking.core.models.entities.Connection;
import com.parking.core.repositories.ConnectionRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaConnectionRepo implements ConnectionRepo{

    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Connection> findAllConnections() {
        Query query = em.createQuery("SELECT b from Connection b");
        return query.getResultList();
    }

    @Override
    public Connection findConnection(Long id) {
        return em.find(Connection.class, id);
    }

    @Override
    public Connection createConnection(Connection data) {
        em.persist(data);
        em.flush();
        return data;
    }

    @Override
    public List<Connection> findConnectionByAccountName(String name) {

        /*
        Query query = em.createQuery("SELECT b from AccountGroup b where b.groupName=?1");
        query.setParameter(1, title);
        List<AccountGroup> accountGroups = query.getResultList();
         */
        Query query = em.createQuery("SELECT b from Connection b where b.initiator.name=?1");
        query.setParameter(1, name);
        return query.getResultList();

    }
}
