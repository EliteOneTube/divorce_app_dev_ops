package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.DivorceDao;
import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.DivorcePaper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;
import java.util.List;


@Controller
@RequestMapping("")
public class IndexController{

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private DivorceDao divorceDao;

    @Autowired
    private MemberInfoDao memberInfoDao;


    @GetMapping("/more_information")
    public String more_information() { return "more_information";}

    @GetMapping("/help")
    public String help() { return "help";}

    @GetMapping("/contact")
    public String contact() { return "contact";}

    @GetMapping("/cdp")
    public String cdp() { return "cdp";}

    @GetMapping("/member_names")
    public String member_names() { return "member_names";}

    @GetMapping("/account_details")
    public String account_details() { return "account_details";}

    @GetMapping("/my_divorces")
    public String my_divorces(Model model, Principal principal) {
        //load user
        UserDetails user = jdbcUserDetailsManager.loadUserByUsername(principal.getName());

        List<DivorcePaper> divorces;
        if(user.getAuthorities().contains(new SimpleGrantedAuthority("ROLE_ADMIN"))){
            divorces = divorceDao.findAll();
        } else {
            divorces = divorceDao.findByUsername(principal.getName());
        }

        model.addAttribute("divorces", divorces);
        return "my_divorces";
    }

    @GetMapping("/members")
    public String members(Model model) {
        model.addAttribute("members", memberInfoDao.findAll());
        return "admin/members";
    }

    @GetMapping("/cu")
    public String create_user() { return "admin/create_user";}
}
