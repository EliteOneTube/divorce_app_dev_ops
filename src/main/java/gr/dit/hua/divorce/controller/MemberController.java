package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.MemberInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberInfoDao memberInfoDao;

    @GetMapping("/getMembers")
    List<MemberInfo> getMembers() {
        return memberInfoDao.findAll();
    }

    @GetMapping("/getMember")
    MemberInfo getMember(String taxNumber) {
        if(memberInfoDao.findByTaxNumber(taxNumber) != null) {
            return memberInfoDao.findByTaxNumber(taxNumber);
        }

        //send back 404
        throw new ResponseStatusException(
                HttpStatus.NOT_FOUND, "Member not found"
        );
    }

}
