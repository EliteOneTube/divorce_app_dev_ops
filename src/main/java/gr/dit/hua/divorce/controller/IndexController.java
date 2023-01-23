package gr.dit.hua.divorce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("")
public class IndexController{
    @GetMapping("/notary")
    public String notary() { return "notary";}

    @GetMapping("/admin/index")
    public String admin() { return "admin/index";}

    @GetMapping("/spouse")
    public String spouse() { return "spouse";}

    @GetMapping("/lawyer")
    public String lawyer() { return "lawyer";}

    @GetMapping("/more_information")
    public String more_information() { return "more_information";}

    @GetMapping("/login")
    public String login() { return "login";}

    @GetMapping("/help")
    public String help() { return "help";}
}
