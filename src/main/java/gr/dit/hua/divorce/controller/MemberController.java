package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.DivorceDao;
import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.DivorcePaper;
import gr.dit.hua.divorce.entity.MemberInfo;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberInfoDao memberInfoDao;

    @Autowired
    private DivorceDao divorceDao;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;
    @GetMapping("/getMembers")
    List<MemberInfo> getMembers() {
        return memberInfoDao.findAll();
    }

    @GetMapping("/getMember")
    MemberInfo getMember(String taxNumber, HttpServletResponse response) {
        if(memberInfoDao.findByTaxNumber(taxNumber) != null) {
            return memberInfoDao.findByTaxNumber(taxNumber);
        }

        //send back 404
        response.setStatus(404);
        return null;
    }

    @PostMapping("/deleteMember/{taxNumber}")
    public void deleteMember(@PathVariable String taxNumber, HttpServletResponse response) {
        List<DivorcePaper> divorces = divorceDao.findByTaxNumber(taxNumber);

        for(DivorcePaper divorce : divorces) {
            divorceDao.deleteById(divorce.getId());
        }

        jdbcUserDetailsManager.deleteUser(memberInfoDao.findByTaxNumber(taxNumber).getUsername());

        if(memberInfoDao.findByTaxNumber(taxNumber) != null) {
            memberInfoDao.deleteByTaxNumber(taxNumber);
            response.setStatus(200);
            return;
        }

        //send back 404
        response.setStatus(404);
    }
}
