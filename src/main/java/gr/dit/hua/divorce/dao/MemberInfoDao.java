package gr.dit.hua.divorce.dao;

import gr.dit.hua.divorce.entity.MemberInfo;

import java.util.List;

public interface MemberInfoDao {
    public MemberInfo findByTaxNumber(String taxNumber);

    public void save(MemberInfo memberInfo);

    public void deleteByTaxNumber(String taxNumber);

    public void deleteAll();

    public List<MemberInfo> findAll();

    public MemberInfo findByUsername(String username);

}
