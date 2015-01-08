package com.parking.core.repositories.jpa;

import com.parking.core.models.entities.Group;
import com.parking.core.repositories.GroupRepo;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class jpaGroupRepo implements GroupRepo {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Group createGroup(Group data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<Group> findAllGroups() {
        Query query = em.createQuery("SELECT b from Group b");
        return query.getResultList();
    }

    @Override
    public Group findGroup(Long id) {
        return em.find(Group.class, id);
    }

    @Override
    public Group findGroupByTitle(String title) {
        Query query = em.createQuery("SELECT b from Group b where b.groupName=?1");
        query.setParameter(1, title);
        List<Group> Groups = query.getResultList();
        if (Groups.isEmpty()) {
            return null;
        } else {
            return Groups.get(0);
        }
    }

    @Override
    public List<Group> findGroupsByAccount(Long accountId) {
        Query query = em.createQuery("SELECT b from Group b where b.owner.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }
}
