package ru.geekbrains;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String root() {
        return "welcome";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
