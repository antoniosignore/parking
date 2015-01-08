package com.parking.core.repositories;

import com.parking.core.models.entities.Account;
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
public class GroupRepoTest {

    @Autowired
    private GroupRepo repo;

    private Group Group;

    @Autowired
    private AccountRepo accountRepo;

    private Account account;


    @Before
    @Transactional
    @Rollback(false)
    public void setup() {
        account = new Account();
        account.setName("name");
        account.setPassword("password");
        accountRepo.createAccount(account);

        Group = new Group();
        Group.setName("IBM");
        Group.setOwner(account);
        repo.createGroup(Group);
    }

    @Test
    @Transactional
    public void testFind() {
        Group Group = repo.findGroup(this.Group.getId());
        assertNotNull(Group);
        assertEquals(Group.getName(), "IBM");
    }

    @Test
    @Transactional
    public void testFindByAccountAndTicker() {
        Group Group = repo.findGroupByAccountAndTicker(account.getId(), "IBM");
        assertNotNull(Group);
        assertEquals(Group.getName(), "IBM");
    }

    @Test
    @Transactional
    public void testFindAllGroups() {
        List<Group> Groups = repo.findAllGroups();
        assertNotNull(Groups);
        assertEquals(Groups.size(), 1);
    }

    @Test
    @Transactional
    public void testFindAllGroupsByAccount() {
        List<Group> Groups = repo.findGroupsByAccount(account.getId());
        assertNotNull(Groups);
        assertEquals(Groups.size(), 1);
    }

}
