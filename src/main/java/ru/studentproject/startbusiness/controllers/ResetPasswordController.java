package ru.studentproject.startbusiness.controllers;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import ru.studentproject.startbusiness.dto.PasswordResetDto;

import ru.studentproject.startbusiness.models.PasswordResetToken;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.repos.PasswordResetTokenRepository;

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
    @ModelAttribute("passwordResetForm")
    public PasswordResetDto resetPasswordDTO(){
        return new PasswordResetDto();
    }

    private void saveNewPassword(PasswordResetDto form){
        PasswordResetToken token = passwordResetTokenRepository.findByToken(form.getToken());

        User user = token.getUser();

        String updatedPassword = passwordEncoder.encode(form.getPassword());
        userService.updatePassword(updatedPassword, user.getId());

        passwordResetTokenRepository.delete(token);
    }
    private void setRedirectAttributes(PasswordResetDto form,
                                                     BindingResult result,RedirectAttributes redirectAttributes){

        redirectAttributes.addFlashAttribute(BindingResult.class.getName()+".passwordResetForm",result);
        redirectAttributes.addFlashAttribute("passwordResetForm",form);

    }

    @GetMapping
    public String resetPasswordPage(@ModelAttribute("passwordResetForm") PasswordResetDto form,@RequestParam(required = true) String token, Model model){
        System.out.println("\nRESET\ntoken = " + token + ", model = " + model);

        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);

        if(resetToken == null){
            System.out.println("Не можем найти токен");

            return "redirect:/forgot-password?notoken";
        }
        else if(resetToken.isExpired()){
            System.out.println("Время действия токена вышло");

            passwordResetTokenRepository.delete(resetToken);

            return "redirect:/forgot-password?tokenexpired";

        }else{
            model.addAttribute("token", resetToken.getToken());
        }
        return "reset-password";
    }
    @PostMapping
    @Transactional
    public String handlerPasswordReset(@ModelAttribute("passwordResetForm") @Valid PasswordResetDto form,
                                       BindingResult result,
                                       RedirectAttributes redirectAttributes){

        System.out.println("\nRESET\n");

        if (result.hasErrors()){
            System.out.println(result.getAllErrors());

            setRedirectAttributes(form, result,redirectAttributes);

            return "redirect:/reset-password?token=" + form.getToken();
        }
        saveNewPassword(form);

        return "redirect:/login?resetSuccess";
    }
}
