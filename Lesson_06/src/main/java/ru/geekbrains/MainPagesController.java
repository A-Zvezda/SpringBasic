package ru.geekbrains;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainPagesController {
    @GetMapping("/")
    public String root() {
        return "/person";
    }
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}
