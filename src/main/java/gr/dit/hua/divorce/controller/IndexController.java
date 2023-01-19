package gr.dit.hua.divorce.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController{

    @GetMapping("/")
    public String index() { return "index";}

    @GetMapping("/notary")
    public String notary() { return "notary";}

    @GetMapping("/admin/index")
    public String admin() { return "admin/index";}

    @GetMapping("/spouse")
    public String spouse() { return "spouse";}

    @GetMapping("/lawyer")
    public String lawyer() { return "lawyer";}

}
