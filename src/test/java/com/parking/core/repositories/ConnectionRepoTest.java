package com.parking.core.repositories;

import com.parking.core.models.entities.*;
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
@ContextConfiguration("classpath:spring/business-config-test.xml")
public class ConnectionRepoTest {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountGroupRepo accountGroupRepo;

    @Autowired
    private ConnectionRepo connectionRepo;

    private Account antonio;
    private Account mida;
    AccountGroup friends;
    AccountGroup family;

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

        friends = new AccountGroup();
        friends.setGroupName("friends");
        friends.setGroupDesc("I miei amici");
        accountGroupRepo.createAccountGroup(friends);

        family = new AccountGroup();
        family.setGroupName("colleghi");
        family.setGroupDesc("I miei amici");
        accountGroupRepo.createAccountGroup(family);

        antonio.addAccountGroup(friends);
        antonio.addAccountGroup(family);

        mida.addAccountGroup(friends);
        mida.addAccountGroup(family);

        accountRepo.createAccount(antonio);
        accountRepo.createAccount(mida);
    }

    @Test
    @Transactional
    public void testCreateConnection() {

        Connection connection = new Connection();

        connection.setInitiator(antonio);
        connection.setReceiver(mida);

        connection.setInitiatorGroup(friends);

        connectionRepo.createConnection(connection);


    }

    @Test
    @Transactional
    public void testFindAccountByName() {
        Account antonio1 = accountRepo.findAccountByName("antonio");
        assertNotNull(antonio1);
        assertEquals(antonio.getName(), "antonio");
        assertEquals(antonio.getPassword(), "ciccionazzo");
    }

    @Test
    @Transactional
    public void testFindAllAccounts() {
        List<Account> allAccounts = accountRepo.findAllAccounts();
        assertEquals(2, allAccounts.size());
    }

}
