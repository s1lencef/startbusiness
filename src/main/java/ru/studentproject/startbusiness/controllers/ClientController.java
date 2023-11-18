package ru.studentproject.startbusiness.controllers;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class ClientController {

    @GetMapping("/account")
    public String login(@AuthenticationPrincipal User user) {
        if (user != null){
            return "redirect:/";
        }
        return "account";
    }

    @GetMapping("/form")
    public String home() {
        return "form";
    }
}