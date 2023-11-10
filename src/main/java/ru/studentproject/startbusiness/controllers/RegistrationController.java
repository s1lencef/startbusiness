package ru.studentproject.startbusiness.controllers;

import jakarta.validation.Valid;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.User;
import org.springframework.validation.BindingResult;
import ru.studentproject.startbusiness.dto.UserRegistrationDto;
import ru.studentproject.startbusiness.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/registration")
public class RegistrationController {

    private UserService userService;

    public RegistrationController(UserService userService) {
        super();
        this.userService = userService;
    }

    @ModelAttribute("user")
    public UserRegistrationDto userRegistrationDto() {
        return new UserRegistrationDto();
    }

    @GetMapping
    public String showRegistrationForm(@AuthenticationPrincipal User user) {
        if (user != null){
            return "redirect:/";
        }
        return "registration";
    }

    @PostMapping
    public String registerUserAccount(@Valid @ModelAttribute("user")
                                      UserRegistrationDto registrationDto, BindingResult result) {
        if (result.hasErrors()){
            return "redirect:/registration?emailInvalid";
        }
        try {
            userService.save(registrationDto);
        }catch(Exception e)
        {
            System.out.println(e);
            return "redirect:/registration?emailUsed";
        }
        return "redirect:/registration?success";
    }
}