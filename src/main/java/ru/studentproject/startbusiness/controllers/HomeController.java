package ru.studentproject.startbusiness.controllers;


import org.springframework.beans.factory.annotation.Autowired;
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
    public String login(@AuthenticationPrincipal User user) {
        if (user != null){
            return "redirect:/home";
        }
        return "login";
    }

    @GetMapping("/")
    public String index() {

        return "redirect:/home";
    }
    @GetMapping("/home")
    public String home( Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        ru.studentproject.startbusiness.models.User user = userService.findByEmail(email);

        String name = userService.findByEmail(email).getFirstName();
        model.addAttribute("name",name);
        List<ru.studentproject.startbusiness.models.User> users = userService.getAll();
        System.out.println(users.toString());


        return "home";
    }

}