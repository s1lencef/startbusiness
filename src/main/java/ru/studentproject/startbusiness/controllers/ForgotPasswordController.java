package ru.studentproject.startbusiness.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.studentproject.startbusiness.dto.PasswordForgotDto;
import ru.studentproject.startbusiness.models.Email;
import ru.studentproject.startbusiness.models.PasswordResetToken;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.repos.PasswordResetTokenRepository;
import ru.studentproject.startbusiness.service.EmailService;
import ru.studentproject.startbusiness.service.UserService;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Controller
@RequestMapping("/forgot-password")
public class ForgotPasswordController{
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;
    @Autowired
    private EmailService emailService;
    @ModelAttribute("forgotPasswordForm")
    public PasswordForgotDto forgotPasswordDTO(){
        return new PasswordForgotDto();
    }
    @GetMapping
    public String forgotPasswordPage(){
        return "forgotpassword";
    }

    @PostMapping
    public String forgotPasswordForm(
            @ModelAttribute("forgotPasswordForm") @Valid PasswordForgotDto form,
            BindingResult result,
            HttpServletRequest request){

        if (result.hasErrors()){
            return "forgotpassword";
        }

        User user = userService.findByEmail(form.getEmail());
        if(user == null){
            result.rejectValue("templates/email",null,"Пользователь с таким адресом электронной " +
                    "почты не зарегистрирован");
            return "forgotpassword";
        }

        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        passwordResetTokenRepository.save(token);

        Email email = new Email();
        email.setFrom("no-replay@startbusiness.ru");
        email.setTo(user.getEmail());
        email.setSubject("Восстановление пароля");

        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("token", token);

        String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

        model.put("resetUrl", url + "/reset-password?token=" + token.getToken());

        email.setModel(model);
        emailService.sendEmail(email);

        return "redirect:/forgot-password?success";
    }
}
