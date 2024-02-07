package ru.studentproject.startbusiness.controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.studentproject.startbusiness.service.UserService;

import java.util.List;


@Controller
public class HomeController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/")
    public String index() {

        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home( Model model) {

        ru.studentproject.startbusiness.models.User user = userService.getAuthenticatedUser();

        model.addAttribute("user",user);

        return "home";
    }
    @GetMapping("/unknown")
    public String unknown(){
        return "unknown";
    }

}