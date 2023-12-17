package ru.studentproject.startbusiness.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.studentproject.startbusiness.dto.FormDto;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;

import ru.studentproject.startbusiness.service.FormService;
import ru.studentproject.startbusiness.service.StatusService;
import ru.studentproject.startbusiness.service.TaxService;
import ru.studentproject.startbusiness.service.UserService;

import java.util.List;


@Controller
public class ClientController {
    @Autowired
    UserService userService;
    @Autowired
    FormService formService;
    @Autowired
    StatusService statusService;
    @Autowired
    TaxService taxService;

    @ModelAttribute("newForm")
    public FormDto formDto() {
        return new FormDto();
    }


    @GetMapping("/profile")
    public String account(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) Long id, Model model) {
        if(id == null){
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            User curr_user = userService.findByEmail(email);
            System.out.println("redirect:/profile?id="+curr_user.getId());
            return "redirect:/profile?id="+curr_user.getId();
        }
        else {
            System.out.println(", id = " + id + "   " + userService.getById(id));
            User user = userService.getById(id);
            List<Form> forms = formService.getUsersForms(user);
            System.out.println("forms = " + forms);
            model.addAttribute("user", user);
            model.addAttribute("forms", forms);

        }
        model.addAttribute("status","choose");
        return "profile";
    }


    @GetMapping("/form/change")
    public String newForm(Model model, @RequestParam(required = true) Long id) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User curr_user = userService.findByEmail(email);
        Form curr_form = formService.get(id);
        if (curr_user != curr_form.getUser()){
            return "redirect:/unknown";
        }
        List<Form> forms = formService.getUsersForms(curr_user);

        model.addAttribute("user", curr_user);
        model.addAttribute("forms", forms);
        String status = "choose";


        if (curr_form.getStatus() == statusService.get(1L)){
            status = "change";
        }
        else if (curr_form.getStatus() == statusService.get(2L)){
            status = "inwork";
        }
        else if (curr_form.getStatus() == statusService.get(4L)){
            status = "done";
        }
        model.addAttribute("status",status);
        model.addAttribute("curr_form", curr_form);
        return "profile";
    }
    @GetMapping("/form/create")
    public String createForm(Model model, @RequestParam(required = true) Long taxId){
        Form form;

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User curr_user = userService.findByEmail(email);

         form = formService.save(taxId,curr_user);
         Long id = form.getId();

        return "redirect:/form/change?id="+id;
    }
    @PostMapping("/form/change")
    public String saveForm(Model model, @RequestParam(required = true) Long id,@ModelAttribute("newForm")
    FormDto formDto, BindingResult result) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User curr_user = userService.findByEmail(email);

        Form curr_form = formService.get(id);

        if (curr_user != curr_form.getUser()){
            return "redirect:/unknown";
        }

        List<Form> forms = formService.getUsersForms(curr_user);

        model.addAttribute("user", curr_user);
        model.addAttribute("forms", forms);


        String status = "choose";

        curr_form.setStatus(statusService.get(2L));

        curr_form = formService.save(curr_form);


        model.addAttribute("status",status);
        model.addAttribute("curr_form", curr_form);








        return "profile";


    }
}