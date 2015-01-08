package com.parking.core.repositories;

import com.parking.core.models.entities.Account;
import com.parking.core.models.entities.AccountGroup;
import org.junit.Before;
import org.junit.Ignore;
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
    private AccountRepo accountRepo;

    @Autowired
    private GroupRepo groupRepo;

    private Account antonio;
    private Account mida;

    AccountGroup family = new AccountGroup();
    AccountGroup friends = new AccountGroup();

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

        family = new AccountGroup();
        family.setGroupName("family");
        family.setGroupDesc("la mia famiglia");
        groupRepo.createGroup(family);

        groupRepo.createGroup(family);

        friends = new AccountGroup();
        friends.setGroupName("friends");
        friends.setGroupDesc("I miei amici");
        groupRepo.createGroup(friends);

        antonio.addAccountGroup(family);
        antonio.addAccountGroup(friends);

        mida.addAccountGroup(family);
        mida.addAccountGroup(friends);

        accountRepo.createAccount(antonio);
        accountRepo.createAccount(mida);
    }

    @Test
    @Transactional
    public void testFind() {
        AccountGroup AccountGroup = groupRepo.findGroup(this.family.getId());
        assertNotNull(AccountGroup);
        assertEquals(AccountGroup.getGroupName(), "family");
    }

    @Test
    @Transactional
    public void testFindAllGroups() {
        List<AccountGroup> accountGroups = groupRepo.findAllGroups();
        assertNotNull(accountGroups);
        assertEquals(accountGroups.size(), 2);
    }


}
