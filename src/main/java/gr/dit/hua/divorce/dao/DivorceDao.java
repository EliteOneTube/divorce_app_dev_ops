package gr.dit.hua.divorce.dao;

import gr.dit.hua.divorce.entity.DivorcePaper;

import java.util.List;

public interface DivorceDao {
    public List<DivorcePaper> findAll();
    public void save(DivorcePaper divorce);
    public DivorcePaper findById(Integer id);

    public void deleteById(Integer id);
}
