package ru.studentproject.startbusiness.controllers;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.python.core.PyInteger;
import org.python.util.PythonInterpreter;
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
import ru.studentproject.startbusiness.models.*;

import ru.studentproject.startbusiness.repos.DocumentRepository;
import ru.studentproject.startbusiness.service.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
    @Autowired
    CompanyService companyService;
    @Autowired
    EmployerService employerService;
    @Autowired
    SubjectService subjectService;
    @Autowired
    DocumentRepository documentRepository;


    @GetMapping("/profile")
    public String account(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) Long id, Model model) throws IOException {
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
            for(Form form:forms){
            }
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
        else if (curr_form.getStatus() == statusService.get(3L)){
            status = "unpaid";
        }
        else if (curr_form.getStatus() == statusService.get(4L)){
            List<Document> documents = documentRepository.findByForm(curr_form.getId());
            model.addAttribute("documents",documents);
            status = "done";
        }
        model.addAttribute("status",status);
        model.addAttribute("curr_form", curr_form);
        model.addAttribute("newForm",new FormDto());
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
    public String saveForm(Model model, @RequestParam(required = true) Long id, @ModelAttribute()
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




        model.addAttribute("status",status);
        model.addAttribute("curr_form", curr_form);

        Company company = new Company();
        company.setForm(curr_form);
        company.setMainActivities(formDto.getMainActivities());
        company.setActivities(formDto.getActivities());
        company.setSubject(subjectService.findByName(formDto.getSubject()));
        company.setLocality(formDto.getLocality());
        company.setStreet(formDto.getStreet());
        company.setBuilding(formDto.getBuilding());
        company.setOffice(formDto.getOffice());

        company = companyService.save(company);


        Employer employer = getEmployer(formDto);
        employer.setForm(curr_form);

        employer = employerService.save(employer);

        curr_form.setStatus(statusService.get(3L));

        curr_form = formService.save(curr_form);


        return "redirect:/profile?id="+curr_user.getId();


    }
    private void makeDocuments(FormDto formDto){
        PythonInterpreter pythonInterpreter = new PythonInterpreter();
        String str = formDto.toString();
        pythonInterpreter.execfile(" python E:\\LETI\\3_kurs\\IT-Projects\\startbusiness\\src\\main\\resources\\python\\IP.py ");


    }
    private void makeDocuments() throws IOException {
        FormDto formDto = new FormDto();
        String str = formDto.toString();
        String[] cmd ={
                "python",
                "E:/LETI/3_kurs/IT-Projects/startbusiness/src/main/java/ru/studentproject/startbusiness/controllers/IP.py",
                "1000"
        };
        Runtime.getRuntime().exec(cmd);

    }

    @GetMapping("/form/delete")
    public String deleteForm(Model model,@RequestParam(required = true) Long id ){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User curr_user = userService.findByEmail(email);
        List<Form> forms = formService.getUsersForms(curr_user);
        model.addAttribute("forms",forms);
        Form form;
        try {
            form = formService.get(id);
        }
        catch (Exception e){
            return "redirect:/profile";
        }
        Company company = companyService.findByForm(form);
        Employer employer = employerService.findByForm(form);
        List<Document> docs = documentRepository.findByForm(form.getId());

        companyService.delete(company);
        employerService.delete(employer);
        for(Document doc:docs){
            documentRepository.delete(doc);
        }
        formService.delete(form);
        model.addAttribute("status","deleted");
        model.addAttribute("delete_id",id);
        return "profile";
    }
    @GetMapping("/form/pay")
    public String pay(Model model,@RequestParam(required = true) Long id ){
        Form form;
        try {
            form = formService.get(id);
        }
        catch (Exception e){
            return "redirect:/profile";
        }
        form.setStatus(statusService.get(2L));
        form.setStaff(userService.getById(24L));
        formService.save(form);

        return "redirect:/profile";
    }
    private static Employer getEmployer(FormDto formDto) {
        Employer employer = new Employer();
        employer.setNumber(formDto.getNumber());
        employer.setBirthDate(formDto.getBirthDate());
        employer.setBirthPlace(formDto.getBirthPlace());
        employer.setCitizenship(formDto.getCitizenship());
        employer.setEmail(formDto.getEmail());
        employer.setINN(formDto.getiNN());
        employer.setSurname(formDto.getLastName());
        employer.setName(formDto.getFirstName());
        employer.setMiddlename(formDto.getMiddleName());
        employer.setSex(formDto.getSex());
        employer.setPhone(formDto.getPhone());
        employer.setDocumentType(formDto.getDocumentType());
        employer.setIssueCode(formDto.getIssueCode());
        employer.setIssueDate(formDto.getIssueDate());
        employer.setIssuePlace(formDto.getIssuePlace());
        return employer;
    }
}