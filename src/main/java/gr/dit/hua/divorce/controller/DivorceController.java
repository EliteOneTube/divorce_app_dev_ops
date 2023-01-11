package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.DivorceDao;
import gr.dit.hua.divorce.entity.DivorcePaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/divorce")
public class DivorceController {


    @Autowired
    private DivorceDao divorceDao;

    @PostMapping("/deleteDivorce")
    public void deleteDivorce(Integer id) {
        divorceDao.deleteById(id);
    }

    @GetMapping("/getDivorce")
    public DivorcePaper getDivorce(Integer id) {
        return divorceDao.findById(id);
    }

    @GetMapping("/getDivorces")
    public Iterable<DivorcePaper> getDivorces() {
        return divorceDao.findAll();
    }

    @PostMapping("/saveDivorce")
    public void saveDivorce(DivorcePaper divorce) {
        divorceDao.save(divorce);
    }
}
