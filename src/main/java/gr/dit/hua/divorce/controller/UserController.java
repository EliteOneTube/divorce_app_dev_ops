package gr.dit.hua.divorce.controller;

import gr.dit.hua.divorce.dao.MemberInfoDao;
import gr.dit.hua.divorce.entity.MemberInfo;
import gr.dit.hua.divorce.entity.Users;
import gr.dit.hua.divorce.templates.UserDetails;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
    public String processRegister(@Valid @RequestBody UserDetails userRegistrationObject, HttpServletResponse response) {
        if(userRegistrationObject.getRole().toUpperCase().equals("ADMIN")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            return "Admins cannot be registered";
        }

        // authorities to be granted
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRegistrationObject.getRole().toUpperCase()));

        if(jdbcUserDetailsManager.userExists(userRegistrationObject.getUsername())) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            return "Username already exists";
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

        return "Registered successfully";
    }

    @PostMapping("/login")
    public boolean login(@Valid @RequestBody Users userLoginObject) {
        if (jdbcUserDetailsManager.loadUserByUsername(userLoginObject.getUsername()) != null) {
            if (passwordEncoder.matches(userLoginObject.getPassword(), jdbcUserDetailsManager.loadUserByUsername(userLoginObject.getUsername()).getPassword())) {
                return true;
            }
        }

        return false;
    }
}
