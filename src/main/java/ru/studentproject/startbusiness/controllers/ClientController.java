package ru.studentproject.startbusiness.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;

import ru.studentproject.startbusiness.service.FormService;
import ru.studentproject.startbusiness.service.UserService;

import java.util.List;


@Controller
public class ClientController {
    @Autowired
    UserService userService;
    @Autowired
    FormService formService;
    @GetMapping("/profile")
    public String account(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = true) Long id, Model model) {
        System.out.println(", id = " + id + "   " + userService.getById(id) );
        User user = userService.getById(id);
        List<Form> forms= formService.getUsersForms(user);
        System.out.println("forms = " + forms);
        model.addAttribute("user",user);
        model.addAttribute("forms",forms);
        return "profile";
    }

    @GetMapping("/form")
    public String home() {
        return "form";
    }
}