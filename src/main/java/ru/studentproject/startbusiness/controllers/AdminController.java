package ru.studentproject.startbusiness.controllers;

import org.python.util.PythonInterpreter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import ru.studentproject.startbusiness.dto.ActivitiesDto;
import ru.studentproject.startbusiness.dto.FormDto;
import ru.studentproject.startbusiness.models.Company;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.service.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;


@Controller
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    FormService formService;
    @Autowired
    UserService userService;
    @Autowired
    CompanyService companyService;
    @Autowired
    StatusService statusService;
    @Autowired
    TaxService  taxService;
    @GetMapping()
    public String adminHome(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User curr_user = userService.findByEmail(email);
        List<Form> forms = formService.getAdminForms(curr_user);
        System.out.println(forms.toString());
        model.addAttribute("forms",forms);
        model.addAttribute("type","check");
        return "admin_home";
    }
    @GetMapping("/form/change")
    public String checkForm(@RequestParam(required = false) Long id, Model model){
        System.out.println("admin change");
        Form curr_form = formService.get(id);
        Company company = companyService.findByForm(curr_form);
        System.out.println(company.getMainActivities());
        model.addAttribute("curr_form",curr_form);
        model.addAttribute("curr_company",company);
        model.addAttribute("type","change");
        model.addAttribute("activ",new ActivitiesDto());

        return "admin_home";
    }
    @PostMapping("/form/change")
    public String saveForm(@RequestParam(required = false) Long id, Model model,@ModelAttribute() ActivitiesDto aDto, BindingResult result){
        Form curr_form = formService.get(id);
        Company company = companyService.findByForm(curr_form);
        company.setMainActivities(aDto.getMainActivities());
        company.setActivities(aDto.getActivities());
        company = companyService.save(company);
        curr_form.setStatus(statusService.get(5L));
        curr_form = formService.save(curr_form);
        return"redirect:/admin/form/make?id="+curr_form.getId();
    }
    @GetMapping("/form/make")
    public String makeDocs(@RequestParam(required = false) Long id, Model model) throws IOException, InterruptedException {
        String scriptPath = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\src\\main\\java\\ru\\studentproject\\startbusiness\\pythonScripts\\IP.py";

        ProcessBuilder Process_Builder = new
                ProcessBuilder("python",scriptPath)
                .inheritIO();

        Process Demo_Process = Process_Builder.start();
        Demo_Process.waitFor();

        BufferedReader Buffered_Reader = new BufferedReader(
                new InputStreamReader(
                        Demo_Process.getInputStream()
                ));
        String Output_line = "";

        while ((Output_line = Buffered_Reader.readLine()) != null) {
            System.out.println(Output_line);
        }
        Form form = formService.get(id);
        if (form.getTax() == taxService.get(2L)){
            System.out.println("импотент");
            scriptPath = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\src\\main\\java\\ru\\studentproject\\startbusiness\\pythonScripts\\Patent.py";
        }
        else{
            System.out.println("Утка");
            scriptPath = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\src\\main\\java\\ru\\studentproject\\startbusiness\\pythonScripts\\USN.py";
        }
       Process_Builder = new
                ProcessBuilder("python",scriptPath)
                .inheritIO();
        Demo_Process = Process_Builder.start();

        Demo_Process.waitFor();

       Buffered_Reader = new BufferedReader(
                new InputStreamReader(
                        Demo_Process.getInputStream()
                ));
       Output_line = "";


        form.setStatus(statusService.get(4L));
        form = formService.save(form);

        return "redirect:/admin";
    }


}