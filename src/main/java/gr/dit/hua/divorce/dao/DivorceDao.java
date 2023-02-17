package gr.dit.hua.divorce.dao;

import gr.dit.hua.divorce.entity.DivorcePaper;
import gr.dit.hua.divorce.entity.MemberInfo;
import gr.dit.hua.divorce.templates.DivorceInfo;

import java.util.List;

public interface DivorceDao {
    public List<DivorcePaper> findAll();
    public void save(DivorcePaper divorce);
    public DivorcePaper findById(Integer id);
    public void deleteById(Integer id);
    public List<DivorcePaper> findByTaxNumber(String taxNumber);

    public List<DivorcePaper> findByMembers(DivorceInfo divorceInfo);

    public List<DivorcePaper> findByUsername(String username);
}
