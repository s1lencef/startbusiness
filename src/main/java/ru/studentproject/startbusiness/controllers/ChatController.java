package ru.studentproject.startbusiness.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@Controller
@RequestMapping("/chat")
public class ChatController {
    @GetMapping()
    public String adminHome() {
        return "home";
    }
}

