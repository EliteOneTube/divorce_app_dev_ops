package gr.dit.hua.divorce.dao;

import gr.dit.hua.divorce.entity.MemberInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class MemberInfoDaoImpl implements MemberInfoDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public MemberInfo findByTaxNumber(String taxNumber) {
        return entityManager.find(MemberInfo.class, taxNumber);
    }

    @Override
    @Transactional
    public void save(MemberInfo memberInfo) {
        entityManager.merge(memberInfo);
    }

    @Override
    @Transactional
    public void deleteByTaxNumber(String taxNumber) {
        entityManager.remove(findByTaxNumber(taxNumber));
    }

    @Override
    @Transactional
    public void deleteAll() {
        Session session = entityManager.unwrap(Session.class);
        session.createQuery("delete from MemberInfo").executeUpdate();
    }

    @Override
    @Transactional
    public List<MemberInfo> findAll() {
        Session session = entityManager.unwrap(Session.class);
        return session.createQuery("from MemberInfo", MemberInfo.class).getResultList();
    }

    @Override
    public MemberInfo findByUsername(String username) {
        Session session = entityManager.unwrap(Session.class);
        Query<MemberInfo> query = session.createQuery("from MemberInfo where username=:username", MemberInfo.class);
        query.setParameter("username", username);
        return query.getSingleResult();
    }
}
