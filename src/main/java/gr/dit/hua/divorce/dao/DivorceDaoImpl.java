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

    @Override
    @Transactional
    public void save(DivorcePaper divorce) {
        DivorcePaper divorcePaper = entityManager.merge(divorce);
    }

    @Override
    @Transactional
    public DivorcePaper findById(Integer id) {
        return entityManager.find(DivorcePaper.class, id);
    }

    @Override
    public void deleteById(Integer id) {
        Session session = entityManager.unwrap(Session.class);
        Query query = session.createQuery("delete from DivorcePaper where id=:id");
        query.setParameter("id", id);
        query.executeUpdate();
    }


}

