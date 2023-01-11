package gr.dit.hua.divorce.dao;

import gr.dit.hua.divorce.entity.Acceptance;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Repository;

@Repository
public class AcceptanceDaoImpl implements AcceptanceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void save(Acceptance acceptance) {
        entityManager.merge(acceptance);
    }

    @Override
    @Transactional
    public Acceptance findByTaxNumber(Integer tax_number) {
        return entityManager.find(Acceptance.class, tax_number);
    }

    @Override
    @Transactional
    public void deleteByTaxNumber(Integer tax_number) {
        entityManager.remove(findByTaxNumber(tax_number));
    }
}
