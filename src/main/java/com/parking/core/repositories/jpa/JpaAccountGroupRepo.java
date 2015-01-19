package com.parking.core.repositories.jpa;

import com.parking.core.models.entities.AccountGroup;
import com.parking.core.repositories.AccountRepo;
import com.parking.core.repositories.AccountGroupRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaAccountGroupRepo implements AccountGroupRepo {
    
    @PersistenceContext
    private EntityManager em;

    @Autowired
    AccountRepo accountRepo;

    @Override
    public AccountGroup createAccountGroup(AccountGroup data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<AccountGroup> findAllAccountGroups() {
        Query query = em.createQuery("SELECT b from AccountGroup b");
        return query.getResultList();
    }

    @Override
    public AccountGroup findAccountGroup(Long id) {
        return em.find(AccountGroup.class, id);
    }

    @Override
    public AccountGroup findAccountGroupByName(String title) {
        Query query = em.createQuery("SELECT b from AccountGroup b where b.groupName=?1");
        query.setParameter(1, title);
        List<AccountGroup> accountGroups = query.getResultList();
        if (accountGroups.isEmpty()) {
            return null;
        } else {
            return accountGroups.get(0);
        }
    }

    public AccountGroup deleteAccountGroupEntry(Long id){
        AccountGroup entry = em.find(AccountGroup.class, id);
        em.remove(entry);
        return entry;
    }

    public AccountGroup updateAccountGroupEntry(Long accountGroupId, AccountGroup data){
        AccountGroup entry = em.find(AccountGroup.class, accountGroupId);
        entry.setGroupDesc(data.getGroupDesc());
        entry.setGroupName(data.getGroupName());
        return entry;
    }


}
