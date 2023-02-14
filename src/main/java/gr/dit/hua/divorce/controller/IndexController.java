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

    @GetMapping("/help")
    public String help() { return "help";}

    @GetMapping("/contact")
    public String contact() { return "contact";}

    @GetMapping("/cdp")
    public String cdp() { return "cdp";}

    @GetMapping("/member_names")
    public String member_names() { return "member_names";}

    @GetMapping("/document_details")
    public String document_details() { return "document_details";}

    @GetMapping("/divorce_confirmation")
    public String divorce_confirmation() { return "divorce_confirmation";}

    @GetMapping("/divorce_cancellation")
    public String divorce_cancellation() { return "divorce_cancellation";}

    @GetMapping("/account_details")
    public String account_details() { return "account_details";}

    @GetMapping("/my_divorces")
    public String my_divorces() { return "my_divorces";}
}
