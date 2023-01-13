package gr.dit.hua.divorce.dao;

import gr.dit.hua.divorce.entity.DivorcePaper;
import gr.dit.hua.divorce.entity.MemberInfo;
import gr.dit.hua.divorce.templates.DivorceInfo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Member;
import java.util.List;

@Repository
public class DivorceDaoImpl implements DivorceDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private MemberInfoDaoImpl memberInfoDao;

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
        divorce.setCreated_at(new java.util.Date());
        entityManager.merge(divorce);
    }

    @Override
    @Transactional
    public DivorcePaper findById(Integer id) {
        return entityManager.find(DivorcePaper.class, id);
    }

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
        MemberInfo memberInfo = memberInfoDao.findByTaxNumber(taxNumber);

        return memberInfo.getDivorcePapers();
    }

    @Override
    public List<DivorcePaper> findByMembers(DivorceInfo divorceInfo) {
        MemberInfo spouse1 = memberInfoDao.findByTaxNumber(divorceInfo.getSpouse1());
        MemberInfo spouse2 = memberInfoDao.findByTaxNumber(divorceInfo.getSpouse2());

        if(spouse1.getDivorcePapers().size() > 0 && spouse2.getDivorcePapers().size() > 0) {
            return spouse1.getDivorcePapers();
        } else {
            return null;
        }
    }

}

