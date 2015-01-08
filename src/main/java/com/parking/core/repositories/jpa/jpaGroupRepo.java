package com.parking.core.repositories.jpa;

import com.parking.core.models.entities.Account;
import com.parking.core.models.entities.AccountGroup;
import com.parking.core.repositories.AccountRepo;
import com.parking.core.repositories.GroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

@Repository
public class jpaGroupRepo implements GroupRepo {
    
    @PersistenceContext
    private EntityManager em;

    @Autowired
    AccountRepo accountRepo;

    @Override
    public AccountGroup createGroup(AccountGroup data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<AccountGroup> findAllGroups() {
        Query query = em.createQuery("SELECT b from AccountGroup b");
        return query.getResultList();
    }

    @Override
    public AccountGroup findGroup(Long id) {
        return em.find(AccountGroup.class, id);
    }

    @Override
    public AccountGroup findGroupByName(String title) {
        Query query = em.createQuery("SELECT b from AccountGroup b where b.groupName=?1");
        query.setParameter(1, title);
        List<AccountGroup> accountGroups = query.getResultList();
        if (accountGroups.isEmpty()) {
            return null;
        } else {
            return accountGroups.get(0);
        }
    }

}
