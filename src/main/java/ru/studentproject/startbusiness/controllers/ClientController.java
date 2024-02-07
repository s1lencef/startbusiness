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
import org.springframework.web.multipart.MultipartFile;
import ru.studentproject.startbusiness.config.CountryFounder;
import ru.studentproject.startbusiness.dto.FormDto;
import ru.studentproject.startbusiness.models.*;

import ru.studentproject.startbusiness.repos.DocumentRepository;
import ru.studentproject.startbusiness.repos.RoleRepository;
import ru.studentproject.startbusiness.repos.UserComparator;
import ru.studentproject.startbusiness.service.*;

import java.io.IOException;

import java.util.*;


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
    static SubjectService subjectService;
    @Autowired
    DocumentRepository documentRepository;
    @Autowired
    RoleRepository roleRepository;
    @Autowired
    FileService fileService;

    private final long STATUS_CHANGE = 1L;
    private final long STATUS_IN_WORK = 2L;
    private final long STATUS_UNPAID = 3L;
    private final long STATUS_DONE = 4L;

    private User getStaffToPurpose(){
        final long ROLE_STAFF = 3L;
        List<User> staff = userService.findByRole(ROLE_STAFF);
        staff.sort(new UserComparator());
        User lastStaff = staff.get(0);
        lastStaff.setFormsCount();
        return userService.save(lastStaff);
    }

    private void purposeFormToStaff(Form form, User staff){
        form.setStaff(staff);
        formService.save(form);
    }
    private static Employer getEmployer(FormDto formDto) throws IOException {
        Employer employer = new Employer();
        CountryFounder countryFounder = new CountryFounder();
        System.out.println("formDto = " + formDto.toString());

        employer.setSurname(formDto.getLastName());
        employer.setName(formDto.getFirstName());
        employer.setMiddlename(formDto.getMiddleName());
        employer.setBirthDate(formDto.getBirthDate());
        employer.setBirthPlace(formDto.getBirthPlace());
        employer.setCitizenship(formDto.getCitizenship());
        employer.setSex(formDto.getSex());
        employer.setINN(formDto.getiNN());

        employer.setPhone(formDto.getPhone());
        employer.setEmail(formDto.getEmail());

        employer.setDocumentType(formDto.getDocumentType());
        employer.setIssueCode(formDto.getIssueCode());
        employer.setIssueDate(formDto.getIssueDate());
        employer.setIssuePlace(formDto.getIssuePlace());
        employer.setNumber(formDto.getNumber());

        if (!Objects.equals(formDto.getCountry(), "")){
            employer.setCountry(countryFounder.getCountryCode(formDto.getCountry()));
            employer.setResidentCard(formDto.getResidentCard());
            employer.setResidentCardEndDate(formDto.getResidentCardEndDate());
            employer.setResidentCardIssueDate(formDto.getResidentCardIssueDate());
            employer.setResidentCardNumber(formDto.getResidentCardNumber());
            employer.setResidentCardIssuePlace(formDto.getResidentCardIssuePlace());
            employer.setInfiniteResidentCard(formDto.getInfiniteResidentCard());
        }


        return employer;
    }
    private static Company getCompany(FormDto formDto){
        Company company = new Company();
        company.setMainActivities(formDto.getMainActivities());
        company.setActivities(formDto.getActivities());
        company.setSubject(subjectService.findByName(formDto.getSubject()));
        company.setLocality(formDto.getLocality());
        company.setStreet(formDto.getStreet());
        company.setBuilding(formDto.getBuilding());
        company.setOffice(formDto.getOffice());
        company.setCabinet(formDto.getCabinet());
        return company;
    }
    private String getFormStatus(Form form){
        if (form.getStatus() == statusService.get(STATUS_CHANGE)){
            return "change";
        }
        else if (form.getStatus() == statusService.get(STATUS_IN_WORK)){
            return "inwork";
        }
        else if (form.getStatus() == statusService.get(STATUS_UNPAID)){
            return "unpaid";
        }
        else if (form.getStatus() == statusService.get(STATUS_DONE)){

            return "done";
        }
        return "choose";
    }
    private void printFileInfo(MultipartFile file){
        System.out.println("Загружен файл: " + file.getOriginalFilename());
        System.out.println("Размер файла: " + file.getSize() + " байт");
    }
    @GetMapping("/profile")
    public String account(HttpServletRequest request, HttpServletResponse response, @RequestParam(required = false) Long id, Model model) throws IOException {
        if(id == null){
            User curr_user = userService.getAuthenticatedUser();
            System.out.println("redirect:/profile?id="+curr_user.getId());
            return "redirect:/profile?id="+curr_user.getId();
        }
        else {
            System.out.println(", id = " + id + "   " + userService.getById(id));

            User user = userService.getById(id);
            List<Form> forms = formService.getUsersForms(user);

            model.addAttribute("user", user);
            model.addAttribute("forms", forms);

        }
        model.addAttribute("status","choose");
        return "profile";
    }


    @GetMapping("/form/change")
    public String newForm(Model model, @RequestParam(required = true) Long id) {

        User curr_user = userService.getAuthenticatedUser();

        Form curr_form = formService.get(id);

        if (curr_user != curr_form.getUser()){
            return "redirect:/unknown";
        }
        List<Form> forms = formService.getUsersForms(curr_user);

        String status = getFormStatus(curr_form);

        if (status.equals("done")) {
            List<Document> documents = documentRepository.findByForm(curr_form.getId());
            model.addAttribute("documents", documents);
        }

        model.addAttribute("user", curr_user);
        model.addAttribute("forms", forms);
        model.addAttribute("status",status);
        model.addAttribute("curr_form", curr_form);
        model.addAttribute("newForm",new FormDto());

        return "profile";
    }
    @GetMapping("/form/create")
    public String createForm(Model model, @RequestParam(required = true) Long taxId){
        Form form;

        User curr_user = userService.getAuthenticatedUser();

         form = formService.save(taxId,curr_user);

         Long id = form.getId();

        return "redirect:/form/change?id="+id;
    }
    @PostMapping("/form/change")
    public String saveForm( Model model, @RequestParam(required = true) Long id,
                            @RequestParam("files1") MultipartFile[] files1,
                            @RequestParam(name = "files2", required = false) MultipartFile[] files2,
                            @ModelAttribute() FormDto formDto, BindingResult result) throws IOException
    {
        System.out.println("model = " + model + ", id = " + id + ", files1 = " + Arrays.toString(files1) + ", files2 = " + Arrays.toString(files2) + ", formDto = " + formDto + ", result = " + result);


        User curr_user = userService.getAuthenticatedUser();

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

        Company company = getCompany(formDto);

        company.setForm(curr_form);

        company = companyService.save(company);


        Employer employer = getEmployer(formDto);

        employer.setForm(curr_form);

        employer = employerService.save(employer);


        curr_form.setStatus(statusService.get(STATUS_UNPAID));

        curr_form = formService.save(curr_form);


        for (MultipartFile file : files1) {
            if (!file.isEmpty()) {

                fileService.uploadFile(file,curr_user.getEmail(),curr_form);

                printFileInfo(file);
            }
        }

        // Обработка загруженных файлов для второго места
        for (MultipartFile file : files2) {
            if (!file.isEmpty()) {

                fileService.uploadFile(file,curr_user.getEmail(),curr_form);

                printFileInfo(file);
            }
        }

        return "redirect:/profile?id="+curr_user.getId();


    }

    @GetMapping("/form/delete")
    public String deleteForm(Model model,@RequestParam(required = true) Long id ){

        User curr_user = userService.getAuthenticatedUser();

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

        List<Form> forms = formService.getUsersForms(curr_user);

        model.addAttribute("forms",forms);
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


        User staff = getStaffToPurpose();

        purposeFormToStaff(form, staff);

        return "redirect:/profile";
    }


}