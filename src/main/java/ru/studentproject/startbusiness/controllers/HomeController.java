package ru.studentproject.startbusiness.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/login")
    public String login(@AuthenticationPrincipal User user) {
        if (user != null){
            return "redirect:/";
        }
        return "login";
    }

    @GetMapping("/")
    public String home() {
        return "home";
    }
}