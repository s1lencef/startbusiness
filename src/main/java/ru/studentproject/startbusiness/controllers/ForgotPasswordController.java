package ru.studentproject.startbusiness.controllers;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.studentproject.startbusiness.dto.PasswordForgotDto;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.models.Email;
import ru.studentproject.startbusiness.models.PasswordResetToken;
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
    @Autowired
    private MessageSource messageSource;

    private String errorHandler(BindingResult result){
        for (Object obj:result.getAllErrors()){
            if(obj instanceof FieldError fieldError) {
                String code = fieldError.getCode();
                assert code != null;
                return switch (code) {
                    case "NotEmpty" -> "redirect:/forgot-password?emailEmpty";
                    case "Email" -> "redirect:/forgot-password?invalidEmail";
                    default -> "forgotpassword";
                };
            }
        }
        return "redirect:/forgot-password?emailEmpty";
    }

    private PasswordResetToken createUserResetToken(User user){
        PasswordResetToken token = new PasswordResetToken();
        token.setToken(UUID.randomUUID().toString());
        token.setUser(user);
        token.setExpiryDate(30);
        return token;
    }
    private Email createEmail(User user){
        Email newEmail = new Email();
        newEmail.setFrom("startbusineshelp@gmail.com");
        newEmail.setTo(user.getEmail());
        newEmail.setSubject("Восстановление пароля");
        return newEmail;
    }
    private String generateUrl(HttpServletRequest request,PasswordResetToken token){
        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()+
                "/reset-password?token=" + token.getToken();
    }
    private Map<String, Object> createModel (String url,PasswordResetToken token,User user){
        Map<String, Object> model = new HashMap<>();
        model.put("token", token);
        model.put("user", user);
        model.put("resetUrl", url);
        return model;

    }

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
            HttpServletRequest request)
    {
        System.out.println("form = " + form.getEmail() +", request = " + request);
        if (result.hasErrors()){
            return errorHandler(result);
        }

        User user = userService.findByEmail(form.getEmail());
        if(user == null){
            System.out.println("null user");
            return "redirect:/forgot-password?wrongEmail";
        }
        System.out.println("OK");

        PasswordResetToken token = createUserResetToken(user);

        try {
            passwordResetTokenRepository.save(token);
        }catch (Exception e){
            System.out.println(e);
            return "redirect:/forgot-password?error";
        }

        Email email = createEmail(user);

        String url = generateUrl(request,token);

        Map<String, Object> model = createModel(url,token,user);

        email.setModel(model);

        emailService.sendEmail(email);

        return "redirect:/forgot-password?success";
    }
}
