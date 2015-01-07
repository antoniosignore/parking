package tutorial.core.repositories;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import tutorial.core.models.entities.Account;
import tutorial.core.models.entities.dtmc.Stock;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring/business-config.xml")
public class StockRepoTest {

    @Autowired
    private StockRepo repo;

    private Stock stock;

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

        stock = new Stock();
        stock.setName("IBM");
        stock.setOwner(account);
        repo.createStock(stock);
    }

    @Test
    @Transactional
    public void testFind() {
        Stock stock = repo.findStock(this.stock.getId());
        assertNotNull(stock);
        assertEquals(stock.getName(), "IBM");
    }

    @Test
    @Transactional
    public void testFindByAccountAndTicker() {
        Stock stock = repo.findStockByAccountAndTicker(account.getId(), "IBM");
        assertNotNull(stock);
        assertEquals(stock.getName(), "IBM");
    }

    @Test
    @Transactional
    public void testFindAllStocks() {
        List<Stock> stocks = repo.findAllStocks();
        assertNotNull(stocks);
        assertEquals(stocks.size(), 1);
    }

    @Test
    @Transactional
    public void testFindAllStocksByAccount() {
        List<Stock> stocks = repo.findStocksByAccount(account.getId());
        assertNotNull(stocks);
        assertEquals(stocks.size(), 1);
    }

}
