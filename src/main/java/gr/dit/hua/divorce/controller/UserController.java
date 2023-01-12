package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.MemberInfo;
import gr.dit.hua.divorce.entity.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberInfoDao memberInfoDao;

    @PostMapping("/register")
    @ResponseBody
    public String processRegister(@Valid @RequestBody MemberInfo userRegistrationObject) {
        // authorities to be granted
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority(userRegistrationObject.getRole().toUpperCase()));

        if(jdbcUserDetailsManager.userExists(userRegistrationObject.getUsername())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, "Username already exists");
        }

        User user = new User(userRegistrationObject.getUsername(), passwordEncoder.encode(userRegistrationObject.getPassword()), authorities);
        jdbcUserDetailsManager.createUser(user);

        userRegistrationObject.setPassword(null);
        memberInfoDao.save(userRegistrationObject);

        return "Registered successfully";
    }

    @PostMapping("/login")
    @ResponseBody
    public boolean login(@Valid @RequestBody Users userLoginObject) {
        System.out.println(jdbcUserDetailsManager.loadUserByUsername(userLoginObject.getUsername()));
        if (jdbcUserDetailsManager.loadUserByUsername(userLoginObject.getUsername()) != null) {
            if (passwordEncoder.matches(userLoginObject.getPassword(), jdbcUserDetailsManager.loadUserByUsername(userLoginObject.getUsername()).getPassword())) {
                return true;
            }
        }

        return false;
    }
}
