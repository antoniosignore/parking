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

import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class AccountRepoTest {

    @Autowired
    private AccountRepo accountRepo;

    @Autowired
    private AccountGroupRepo accountGroupRepo;

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
        accountGroupRepo.createAccountGroup(family);

        friends = new AccountGroup();
        friends.setGroupName("friends");
        friends.setGroupDesc("I miei amici");
        accountGroupRepo.createAccountGroup(friends);

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
        Account antonio = accountRepo.findAccount(this.antonio.getId());
        Account mida = accountRepo.findAccount(this.mida.getId());

        assertNotNull(antonio);
        assertEquals(antonio.getName(), "antonio");
        assertEquals(antonio.getPassword(), "ciccionazzo");

        assertNotNull(mida);
        assertEquals(mida.getName(), "mida");
        assertEquals(mida.getPassword(), "provolone");

        assertEquals(2, antonio.getAccountGroups().size());
        assertEquals(2, antonio.getAccountGroups().size());

        List<AccountGroup> accountGroups = antonio.getAccountGroups();
        for (Iterator<AccountGroup> iterator = accountGroups.iterator(); iterator.hasNext(); ) {
            AccountGroup obj = iterator.next();
            assertEquals(0, obj.getAccounts().size());
        }
    }

    @Ignore
    @Test
    @Transactional
    public void testDuplicateAccounts() {

        Account dupantonio = new Account();
        dupantonio.setName("antonio");
        dupantonio.setPassword("grassissimo");

        try {
            accountRepo.createAccount(dupantonio);
        } catch (Exception e) {
            e.printStackTrace();
        }

        List<Account> allAccounts = accountRepo.findAllAccounts();

        assertEquals(2, allAccounts.size());
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
