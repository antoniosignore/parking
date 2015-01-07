package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.dtmc.Stock;
import tutorial.core.repositories.StockRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaStockRepo implements StockRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Stock createStock(Stock data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<Stock> findAllStocks() {
        Query query = em.createQuery("SELECT b from Stock b");
        return query.getResultList();
    }

    @Override
    public Stock findStock(Long id) {
        return em.find(Stock.class, id);
    }

    @Override
    public Stock findStockByAccountAndTicker(Long accountId, String ticker) {
        Query query = em.createQuery("SELECT b from Stock b where b.owner.id=?1 and b.name=?2");
        query.setParameter(1, accountId);
        query.setParameter(2, ticker);
        List<Stock> stocks = query.getResultList();
        if (stocks.isEmpty()) {
            return null;
        } else {
            return stocks.get(0);
        }
    }

    @Override
    public List<Stock> findStocksByAccount(Long accountId) {
        Query query = em.createQuery("SELECT b from Stock b where b.owner.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }
}
