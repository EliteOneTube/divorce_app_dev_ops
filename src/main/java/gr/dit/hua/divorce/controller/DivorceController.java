package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.DivorceDao;
import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.Acceptance;
import gr.dit.hua.divorce.entity.DivorcePaper;
import gr.dit.hua.divorce.entity.MemberInfo;
import gr.dit.hua.divorce.templates.DivorceInfo;
import gr.dit.hua.divorce.templates.NotarialInfo;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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

    //TODO: check if the lawyer is the one who is supposed to handle the case
    @PostMapping("/deleteDivorce/{id}")
    public void deleteDivorce(@PathVariable Integer id, Principal principal) {
        //principal.getName()
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

    //TODO: check if users exist
    @PostMapping("/saveDivorce")
    public String saveDivorce(@Valid @RequestBody DivorceInfo divorceInfo, HttpServletResponse response) {
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

        List<DivorcePaper> existingDivorces = divorceDao.findByMembers(divorceInfo);
        if(existingDivorces != null && existingDivorces.size() > 0) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Divorce paper already exists";
        }

        Acceptance acceptanceSpouse1 = new Acceptance();
        acceptanceSpouse1.setAcceptance(false);
        acceptanceSpouse1.setDivorcePaper(divorce);
        acceptanceSpouse1.setMemberInfo(memberInfoDao.findByTaxNumber(divorceInfo.getSpouse1()));

        Acceptance acceptanceSpouse2 = new Acceptance();
        acceptanceSpouse2.setAcceptance(false);
        acceptanceSpouse2.setDivorcePaper(divorce);
        acceptanceSpouse2.setMemberInfo(memberInfoDao.findByTaxNumber(divorceInfo.getSpouse2()));




        divorceDao.save(divorce);

        return "Divorce saved successfully";
    }

    @GetMapping("/getDivorceByTaxNumber")
    public List<DivorcePaper> getDivorceByTaxNumber(String taxNumber) {
        return divorceDao.findByTaxNumber(taxNumber);
    }

    //TODO: check if all users accepted
    @PostMapping("/approveDivorce")
    public String approveDivorce(@RequestBody NotarialInfo notarialInfo, HttpServletResponse response) {
        DivorcePaper divorce = divorceDao.findById(notarialInfo.getId());

        if(divorce == null) {
            response.setStatus(404);
            return "Divorce paper does not exist";
        }

        divorce.setStatus("approved");
        divorce.setNotarialActionId(notarialInfo.getNotarialActionId());
        divorceDao.save(divorce);
        return "Divorce approved successfully";
    }

    @PostMapping("/acceptDivorce")
    public String acceptDivorce(@PathVariable Integer id, HttpServletResponse response) {
        DivorcePaper divorce = divorceDao.findById(id);

        if(divorce == null) {
            response.setStatus(404);
            return "Divorce paper does not exist";
        }


        divorceDao.save(divorce);
        return "Divorce accepted successfully";
    }
}
