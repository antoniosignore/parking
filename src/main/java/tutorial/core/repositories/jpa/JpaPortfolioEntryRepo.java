package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.dtmc.PortfolioEntry;
import tutorial.core.repositories.PortfolioEntryRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaPortfolioEntryRepo implements PortfolioEntryRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public PortfolioEntry findPortfolioEntry(Long id) {
        return em.find(PortfolioEntry.class, id);
    }

    @Override
    public PortfolioEntry deletePortfolioEntry(Long id) {
        PortfolioEntry entry = em.find(PortfolioEntry.class, id);
        em.remove(entry);
        return entry;
    }

    @Override
    public PortfolioEntry updatePortfolioEntry(Long id, PortfolioEntry data) {
        PortfolioEntry entry = em.find(PortfolioEntry.class, id);
        entry.setAmount(data.getAmount());
        return entry;
    }

    @Override
    public PortfolioEntry createPortfolioEntry(PortfolioEntry data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<PortfolioEntry> findByPortfolioId(Long blogId) {
        Query query = em.createQuery("SELECT b FROM PortfolioEntry b WHERE b.portfolio.id=?1");
        query.setParameter(1, blogId);
        return query.getResultList();
    }
}
