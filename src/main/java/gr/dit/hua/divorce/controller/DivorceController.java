package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.DivorceDao;
import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.DivorcePaper;
import gr.dit.hua.divorce.entity.MemberInfo;
import gr.dit.hua.divorce.templates.DivorceInfo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/divorce")
public class DivorceController {


    @Autowired
    private DivorceDao divorceDao;

    @Autowired
    MemberInfoDao memberInfoDao;

    @PostMapping("/deleteDivorce/{id}")
    public void deleteDivorce( @PathVariable Integer id) {
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
    public String saveDivorce(@Valid @RequestBody DivorceInfo divorceInfo) {
        DivorcePaper divorce = new DivorcePaper();
        divorce.setChildSupport(divorceInfo.getChildSupport());
        divorce.setRestoreName(divorceInfo.getRestoreName());
        divorce.setNumberOfChildren(divorceInfo.getNumberOfChildren());

        List<MemberInfo> members = new ArrayList<>();

        members.add(memberInfoDao.findByTaxNumber(divorceInfo.getNotary()));
        members.add(memberInfoDao.findByTaxNumber(divorceInfo.getLawyer1()));
        members.add(memberInfoDao.findByTaxNumber(divorceInfo.getLawyer2()));
        members.add(memberInfoDao.findByTaxNumber(divorceInfo.getSpouse2()));
        members.add(memberInfoDao.findByTaxNumber(divorceInfo.getSpouse1()));

        divorce.setMembers(members);

        divorceDao.save(divorce);

        return "Divorce saved successfully";
    }

    @GetMapping("/getDivorceByTaxNumber")
    public List<DivorcePaper> getDivorceByTaxNumber(String taxNumber) {
        return divorceDao.findByTaxNumber(taxNumber);
    }
}
