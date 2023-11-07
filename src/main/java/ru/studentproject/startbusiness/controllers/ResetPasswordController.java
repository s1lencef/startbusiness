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
    @ModelAttribute("resetPasswordForm")
    public PasswordResetDto resetPasswordDTO(){
        return new PasswordResetDto();
    }
    @GetMapping
    public String resetPasswordPage(@RequestParam(required = true) String token, Model model){
        PasswordResetToken resetToken = passwordResetTokenRepository.findByToken(token);
        if(resetToken == null){
            model.addAttribute("error","Не можем найти токен");
        } else if(resetToken.isExpired()){
            model.addAttribute("error","Время действия токена вышло," +
                    " отправьте запрос на смену пароля повторно");
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

        if (result.hasErrors()){
            System.out.println(result.getAllErrors());
            redirectAttributes.addFlashAttribute(BindingResult.class.getName()+".passwordResetForm",result);
            redirectAttributes.addFlashAttribute("passwordResetForm",form);
            return "redirect:/reset-password?token=" + form.getToken();
        }
        PasswordResetToken token = passwordResetTokenRepository.findByToken(form.getToken());
        User user = token.getUser();
        String updatedPassword = passwordEncoder.encode(form.getPassword());
        userService.updatePassword(updatedPassword, user.getId());
        passwordResetTokenRepository.delete(token);
        return "redirect:/login?resetSuccess";
    }
}
