package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.DivorceDao;
import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.DivorcePaper;
import gr.dit.hua.divorce.entity.MemberInfo;
import gr.dit.hua.divorce.templates.UserDetails;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

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

    @PostMapping("/createMember")
    public String createMember(@RequestBody UserDetails userRegistrationObject, HttpServletResponse response) {
        //If any of the fields is empty
        if(userRegistrationObject.getUsername().isEmpty() || userRegistrationObject.getPassword().isEmpty() || userRegistrationObject.getRole().isEmpty() || userRegistrationObject.getTaxNumber().isEmpty() || userRegistrationObject.getEmail().isEmpty() || userRegistrationObject.getFullName().isEmpty()) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "All fields are required";
        }

        //If the role is not user or admin
        if(userRegistrationObject.getRole().toUpperCase().equals("ADMIN")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "You cannot register as an admin";
        }

        // authorities to be granted
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRegistrationObject.getRole().toUpperCase()));

        //check if username exists
        if(jdbcUserDetailsManager.userExists(userRegistrationObject.getUsername())) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Username already exists";
        }

        //check if email is valid
        if(!userRegistrationObject.getEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Email is not valid";
        }

        //check if tax number is numeric
        try {
            Long.parseLong(userRegistrationObject.getTaxNumber());
        } catch (NumberFormatException e) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Tax number is not valid";
        }

        //check if tax number is valid
        if(userRegistrationObject.getTaxNumber().length() != 9) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Tax number is not valid";
        }

        //check if tax number exists
        if(memberInfoDao.findByTaxNumber(userRegistrationObject.getTaxNumber()) != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Tax number already exists";
        }

        User user = new User(userRegistrationObject.getUsername(), passwordEncoder.encode(userRegistrationObject.getPassword()), authorities);
        jdbcUserDetailsManager.createUser(user);

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setTaxNumber(userRegistrationObject.getTaxNumber());
        memberInfo.setEmail(userRegistrationObject.getEmail());
        memberInfo.setFullName(userRegistrationObject.getFullName());


        memberInfo.setUsername(userRegistrationObject.getUsername());

        memberInfoDao.save(memberInfo);

        response.setStatus(200);
        return "User created successfully";
    }
}
