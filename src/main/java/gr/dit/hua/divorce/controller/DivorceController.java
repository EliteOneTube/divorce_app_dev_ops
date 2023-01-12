package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.DivorceDao;
import gr.dit.hua.divorce.entity.DivorcePaper;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/divorce")
public class DivorceController {


    @Autowired
    private DivorceDao divorceDao;

    @PostMapping("/deleteDivorce")
    public void deleteDivorce(@Valid @RequestBody Integer id) {
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
    public void saveDivorce(@Valid @RequestBody DivorcePaper divorce) {
        divorceDao.save(divorce);
    }
}
