package gr.dit.hua.divorce.dao;

import gr.dit.hua.divorce.entity.DivorcePaper;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class DivorceDaoImpl implements DivorceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public List<DivorcePaper> findAll() {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from DivorcePaper", DivorcePaper.class);
        List<DivorcePaper> divorcePapers = query.getResultList();
        return divorcePapers;
    }

    //TODO: check if divorce paper exists already with all 4 members
    @Override
    @Transactional
    public void save(DivorcePaper divorce) {
        divorce.setCreated_at(new java.util.Date());
        entityManager.merge(divorce);
    }

    @Override
    @Transactional
    public DivorcePaper findById(Integer id) {
        return entityManager.find(DivorcePaper.class, id);
    }

    //TODO: check if it exists
    @Override
    @Transactional
    public void deleteById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from DivorcePaper where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    @Transactional
    public List<DivorcePaper> findByTaxNumber(String taxNumber) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("from DivorcePaper where lawyer1=:taxNumber");
        query.setParameter("taxNumber", taxNumber);
        List<DivorcePaper> divorcePapers = query.getResultList();

        //if the lawyer is not the first lawyer, check if he is the second lawyer
        if (divorcePapers.isEmpty()) {
            query = session.createQuery("from DivorcePaper where lawyer2=:taxNumber");
            query.setParameter("taxNumber", taxNumber);
            divorcePapers = query.getResultList();
        }

        //if the lawyer is not the second lawyer, check if he is the first spouse
        if (divorcePapers.isEmpty()) {
            query = session.createQuery("from DivorcePaper where spouse1=:taxNumber");
            query.setParameter("taxNumber", taxNumber);
            divorcePapers = query.getResultList();
        }

        //if the lawyer is not the first spouse, check if he is the second spouse
        if (divorcePapers.isEmpty()) {
            query = session.createQuery("from DivorcePaper where spouse2=:taxNumber");
            query.setParameter("taxNumber", taxNumber);
            divorcePapers = query.getResultList();
        }

        //last check, if the lawyer is not the second spouse, check if he is the notary
        if (divorcePapers.isEmpty()) {
            query = session.createQuery("from DivorcePaper where notary=:taxNumber");
            query.setParameter("taxNumber", taxNumber);
            divorcePapers = query.getResultList();
        }

        return divorcePapers;
    }


}

