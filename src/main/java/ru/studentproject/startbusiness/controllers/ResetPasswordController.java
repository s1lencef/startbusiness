package ru.studentproject.startbusiness.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.studentproject.startbusiness.models.PasswordResetToken;
import ru.studentproject.startbusiness.repos.PasswordResetTokenRepository;
import ru.studentproject.startbusiness.service.EmailService;
import ru.studentproject.startbusiness.service.UserService;

@Controller
@RequestMapping("/reset-password")
public class ResetPasswordController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @ModelAttribute("resetPasswordForm")
    public PasswordResetDto resetPasswordDTO;
}
