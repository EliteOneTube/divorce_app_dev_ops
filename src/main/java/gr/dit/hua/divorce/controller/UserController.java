package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.dao.MemberInfoDaoImpl;
import gr.dit.hua.divorce.entity.MemberInfo;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
public class UserController {
    @Autowired
    UserDetailsService userDetailsService;

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberInfoDaoImpl memberInfoDaoImpl;

    @PostMapping("/register")
    @ResponseBody
    public String processRegister(@Valid @RequestBody Map<String, String> json) {
        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setFullName(json.get("fullName"));
        memberInfo.setTaxNumber(json.get("taxNumber"));
        memberInfo.setEmail(json.get("email"));
        memberInfo.setUsername(json.get("username"));
        memberInfo.setPassword(passwordEncoder.encode(json.get("password")));
        memberInfo.setEnabled(true);

        String role = json.get("role");

        // authorities to be granted
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(role.toUpperCase()));

        //check if user exists and if it does return to registration page with error message
        try {
            jdbcUserDetailsManager.loadUserByUsername(memberInfo.getUsername());
        } catch (Exception e) {
            memberInfoDaoImpl.save(memberInfo);
            jdbcUserDetailsManager.addUserToGroup(memberInfo.getUsername(), role);
            return "User created successfully";
        }

        throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "User already exists");
    }

    @PostMapping("/login")
    @ResponseBody
    public String processLogin(@Valid @RequestBody MemberInfo memberInfo) {
        System.out.println(jdbcUserDetailsManager.loadUserByUsername(memberInfo.getUsername()));
        if (jdbcUserDetailsManager.loadUserByUsername(memberInfo.getUsername()) != null) {
//            if (passwordEncoder.matches(memberInfo.getPassword(), jdbcUserDetailsManager.loadUserByUsername(memberInfo.getUsername()).getPassword())) {
//                return "Authorized";
//            }
            return "Authorized";
        }

        //if user does not exist or password is incorrect then return 401 error
        throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Username or password is incorrect");
    }
}
