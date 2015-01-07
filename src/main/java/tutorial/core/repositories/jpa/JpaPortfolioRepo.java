package tutorial.core.repositories.jpa;

import org.springframework.stereotype.Repository;
import tutorial.core.models.entities.dtmc.Portfolio;
import tutorial.core.repositories.PortfolioRepo;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class JpaPortfolioRepo implements PortfolioRepo {
    @PersistenceContext
    private EntityManager em;

    @Override
    public Portfolio createPortfolio(Portfolio data) {
        em.persist(data);
        return data;
    }

    @Override
    public List<Portfolio> findAllPortfolios() {
        Query query = em.createQuery("SELECT b from Portfolio b");
        return query.getResultList();
    }

    @Override
    public Portfolio findPortfolio(Long id) {
        return em.find(Portfolio.class, id);
    }

    @Override
    public List<Portfolio> findPortfoliosByAccount(Long accountId) {
        Query query = em.createQuery("SELECT b from Portfolio b where b.owner.id=?1");
        query.setParameter(1, accountId);
        return query.getResultList();
    }

    @Override
    public Portfolio findPortfoliosByAccountAndName(Long accountId, String name) {
        Query query = em.createQuery("SELECT b from Portfolio b where b.owner.id=?1 and b.name=?2");
        query.setParameter(1, accountId);
        query.setParameter(2, name);
        return (Portfolio) query.getSingleResult();
    }
}
