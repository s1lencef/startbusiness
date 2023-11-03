package ru.studentproject.startbusiness.controllers;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.studentproject.startbusiness.config.GenericResponse;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.repos.UserRepository;
import ru.studentproject.startbusiness.service.UserService;

import java.util.UUID;


@Controller
public class ResetPasswordController {
    private UserRepository userRepository;
    private UserService userService;
    @PostMapping("/resetPassword")
    public GenericResponse resetPassword(HttpServletRequest request,
                                         @RequestParam("email") String userEmail) {
        User user = userRepository.findByEmail(userEmail);
        if (user == null) {
            throw new UsernameNotFoundException
                    ("Invalid username or password.");
        }
        String token = UUID.randomUUID().toString();
        userService.createPasswordResetTokenForUser(user, token);
        mailSender.send(constructResetTokenEmail(getAppUrl(request),
                request.getLocale(), token, user));
        return new GenericResponse(
                messages.getMessage("message.resetPasswordEmail", null,
                        request.getLocale()));
    }
}
