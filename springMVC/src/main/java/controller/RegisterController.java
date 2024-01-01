package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RegisterController     {
    @RequestMapping("/home")
    public String home(){
        return "index";
    }
}
