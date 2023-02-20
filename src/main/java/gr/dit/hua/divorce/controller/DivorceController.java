package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.DivorceDao;
import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.Acceptance;
import gr.dit.hua.divorce.entity.DivorcePaper;
import gr.dit.hua.divorce.entity.MemberInfo;
import gr.dit.hua.divorce.templates.DivorceInfo;
import gr.dit.hua.divorce.templates.NotarialInfo;
import jakarta.persistence.PersistenceContext;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.hibernate.validator.internal.util.logging.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TODO: dont let approved divorces to be edited

@RestController
@RequestMapping("/divorce")
public class DivorceController {

    @Autowired
    private DivorceDao divorceDao;

    @Autowired
    MemberInfoDao memberInfoDao;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @PostMapping("/deleteDivorce/{id}")
    public String deleteDivorce(@PathVariable int id, Principal principal, HttpServletResponse response) {
        DivorcePaper divorcePaper = divorceDao.findById(id);

        //check if divorce paper exists
        if(divorcePaper == null) {
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return "Divorce paper does not exist";
        }

        //check if the user is the lawyer
        Boolean exists = false;
        for(MemberInfo memberInfo : divorcePaper.getMembers()) {
            if(memberInfo.getUsername().equals(principal.getName())) {
                exists = true;
                break;
            }
        }

        if(!exists) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "You are not allowed to delete this divorce paper";
        }

        divorceDao.deleteById(id);
        response.setStatus(HttpServletResponse.SC_OK);
        return "Divorce paper deleted successfully";
    }

    @GetMapping("/getDivorce")
    public DivorcePaper getDivorce(int id) {
        return divorceDao.findById(id);
    }

    @GetMapping("/getDivorces")
    public Iterable<DivorcePaper> getDivorces() {
        return divorceDao.findAll();
    }

    //TODO: check roles
    @PostMapping("/saveDivorce")
    public String saveDivorce(@Valid @RequestBody DivorceInfo divorceInfo, HttpServletResponse response, Principal principal) {
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

        if (members.contains(null)) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "One or more users do not exist";
        }

        divorce.setMembers(members);

        List<DivorcePaper> existingDivorces = divorceDao.findByMembers(divorceInfo);
        if(existingDivorces != null && existingDivorces.size() > 0) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Divorce paper already exists";
        }

        MemberInfo lawyer = memberInfoDao.findByUsername(principal.getName());

        Acceptance acceptance = new Acceptance();
        acceptance.setMemberInfo(lawyer);
        acceptance.setDivorcePaper(divorce);

        List<Acceptance> acceptances = new ArrayList<>();

        acceptances.add(acceptance);

        divorce.setAcceptance(acceptances);

        divorce.setStatus("Pending");

        divorceDao.save(divorce);

        response.setStatus(HttpServletResponse.SC_OK);
        return "Divorce saved successfully";
    }
    //TODO: check if user is the same as the one we are searching
    @GetMapping("/getDivorceByTaxNumber")
    public List<DivorcePaper> getDivorceByTaxNumber(String taxNumber) {
        return divorceDao.findByTaxNumber(taxNumber);
    }

    @PostMapping("/approveDivorce/{id}")
    public String approveDivorce(@PathVariable int id, HttpServletResponse response) {
        DivorcePaper divorce = divorceDao.findById(id);

        if(divorce == null) {
            response.setStatus(404);
            return "Divorce paper does not exist";
        }

        if(divorce.getAcceptance().size() != 4) {
            response.setStatus(403);
            return "Not all users have accepted the divorce";
        }

        if(divorce.getNotarialActionId() == null) {
            response.setStatus(403);
            return "You haven't included the notarial action id";
        }


        divorce.setStatus("Approved");
        divorceDao.save(divorce);

        response.setStatus(HttpServletResponse.SC_OK);
        return "Divorce approved successfully";
    }

    @PostMapping("/acceptDivorce/{id}")
    public String acceptDivorce(@PathVariable int id, HttpServletResponse response, Principal principal) {
        DivorcePaper divorce = divorceDao.findById(id);

        if(divorce == null) {
            response.setStatus(404);
            return "Divorce paper does not exist";
        }

        MemberInfo memberInfo = memberInfoDao.findByUsername(principal.getName());

        //check if user is included in divorce
        Boolean exists = false;
        for(MemberInfo member : divorce.getMembers()) {
            if(member.getUsername().equals(principal.getName())) {
                exists = true;
                break;
            }
        }

        if(!exists) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "User is not included in divorce";
        }

        for(Acceptance acceptance : divorce.getAcceptance()) {
            if(acceptance.getMemberInfo().getUsername().equals(principal.getName())) {
                response.setStatus(409);
                return "Divorce has been already accepted";
            }
        }

        Acceptance acceptance = new Acceptance();
        acceptance.setMemberInfo(memberInfoDao.findByUsername(principal.getName()));
        acceptance.setDivorcePaper(divorce);

        List<Acceptance> acceptances = divorce.getAcceptance();
        acceptances.add(acceptance);
        divorce.setAcceptance(acceptances);

        divorceDao.save(divorce);

        response.setStatus(HttpServletResponse.SC_OK);
        return "Divorce accepted successfully";
    }

    @PostMapping("/notarialActionId/{id}")
    public String setNotarialActionId(@PathVariable int id, @RequestBody String notarialId, Principal principal, HttpServletResponse response) {
        //check if divorce exists
        DivorcePaper divorce = divorceDao.findById(id);

        if(divorce == null) {
            response.setStatus(404);
            return "Divorce paper does not exist";
        }

        if(divorce.getNotarialActionId() != null) {
            response.setStatus(409);
            return "Notarial action id has already been set";
        }

        //check if user is included in divorce
        boolean exists = false;
        for(MemberInfo member : divorce.getMembers()) {
            if(member.getUsername().equals(principal.getName())) {
                exists = true;
                break;
            }
        }

        if(!exists) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return "User is not included in divorce";
        }

        //set notarial action id
        divorce.setNotarialActionId(notarialId);

        divorceDao.save(divorce);

        response.setStatus(HttpServletResponse.SC_OK);
        return "Changed notarial action id successfully";
    }
}
