package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.dtmc.Trade;
import tutorial.core.repositories.TradeRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaTradeRepo implements TradeRepo {
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public Trade findTrade(Long id) {
        return em.find(Trade.class, id);
    }

    @Override
    public Trade deleteTrade(Long id) {
        Trade entry = em.find(Trade.class, id);
        em.remove(entry);
        return entry;
    }

    @Override
    public Trade updateTrade(Long id, Trade data) {
        Trade entry = em.find(Trade.class, id);
        entry.setAmount(data.getAmount());
        return entry;
    }

    @Override
    public Trade createTrade(Trade data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<Trade> findByPortfolioEntryId(Long blogId) {
        Query query = em.createQuery("SELECT b FROM Trade b WHERE b.portfolioEntry.id=?1");
        query.setParameter(1, blogId);
        return query.getResultList();
    }
}
