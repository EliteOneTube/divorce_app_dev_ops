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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    JdbcUserDetailsManager jdbcUserDetailsManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private MemberInfoDao memberInfoDao;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public ModelAndView register() {
        return new ModelAndView("register", "user", new UserDetails());
    }

    //TODO return error messages to the user in case of error
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView processRegister(@ModelAttribute("user") UserDetails userRegistrationObject, HttpServletResponse response) {
        if(userRegistrationObject.getRole().toUpperCase().equals("ADMIN")){
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            //return "You cannot register as an admin";
            return new ModelAndView("register", "user", userRegistrationObject);
        }

        // authorities to be granted
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + userRegistrationObject.getRole().toUpperCase()));

        if(jdbcUserDetailsManager.userExists(userRegistrationObject.getUsername())) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            //return "Username already exists";
            return new ModelAndView("register", "user", userRegistrationObject);
        }

        //check if tax number exists
        if(memberInfoDao.findByTaxNumber(userRegistrationObject.getTaxNumber()) != null) {
            response.setStatus(HttpServletResponse.SC_CONFLICT);
            //return "Tax number already exists";
            return new ModelAndView("register", "user", userRegistrationObject);
        }

        User user = new User(userRegistrationObject.getUsername(), passwordEncoder.encode(userRegistrationObject.getPassword()), authorities);
        jdbcUserDetailsManager.createUser(user);

        MemberInfo memberInfo = new MemberInfo();
        memberInfo.setTaxNumber(userRegistrationObject.getTaxNumber());
        memberInfo.setEmail(userRegistrationObject.getEmail());
        memberInfo.setFullName(userRegistrationObject.getFullName());


        memberInfo.setUsername(userRegistrationObject.getUsername());

        memberInfoDao.save(memberInfo);

        return new ModelAndView("redirect:/");
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model, String error, String logout) {
        if (error != null)
            model.addAttribute("errorMsg", "Your username and password are invalid.");

        if (logout != null)
            model.addAttribute("msg", "You have been logged out successfully.");

        return "login";
    }
}
