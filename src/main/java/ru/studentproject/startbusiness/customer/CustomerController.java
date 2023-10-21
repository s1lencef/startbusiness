package ru.studentproject.startbusiness.customer;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.studentproject.startbusiness.security.UserAccountService;

import java.sql.SQLException;

@Controller
@RequestMapping("api/v1/customers")
@RequiredArgsConstructor
public class CustomerController {


    private final CustomerRepository customerRepository;
    private final UserAccountService userAccountService;
    @PostMapping("/register")
    public String createCustomer(@ModelAttribute("customer") Customer customer,
                                 @ModelAttribute("error") String error, Model model){
        error = "";
        try {
            Customer theCustomer = customerRepository.save(customer);
            userAccountService.createUserAccount(theCustomer);
            model.addAttribute("error",error);
            return "redirect:/profile";
        }catch(Exception e){
            error = "Этот адрес электронной почты уже занят";
            model.addAttribute("error",error);
            return "register";
        }
    }
}
