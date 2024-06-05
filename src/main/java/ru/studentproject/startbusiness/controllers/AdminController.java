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
import ru.studentproject.startbusiness.models.Subject;
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

    private static final long TO_FILL_OUT = 5L;

    private static final long PATENT = 2L;
    private static final long DONE = 4L;
    private static final String MAKE_IP_SCRIPT_PATH = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\" +
            "src\\main\\java\\ru\\studentproject\\startbusiness\\pythonScripts\\IP.py";
    private static final String MAKE_IP_PATENT_SCRIPT_PATH = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\" +
            "src\\main\\java\\ru\\studentproject\\startbusiness\\pythonScripts\\Patent.py";
    private static final String MAKE_IP_USN_SCRIPT_PATH = "E:\\LETI\\3_kurs\\5sem\\IT-Projects\\startbusiness\\" +
            "src\\main\\java\\ru\\studentproject\\startbusiness\\pythonScripts\\USN.py";



    private User getCurrentUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return userService.findByEmail(email);
    }
    private void setOKVEDCode(Form form){

    }

    private ProcessBuilder createProcessBuilder(String scriptPath){
        return new
                ProcessBuilder("python",scriptPath)
                .inheritIO();
    }
    private BufferedReader createBufferedReader(Process Demo){
        return new BufferedReader(
                new InputStreamReader(
                        Demo.getInputStream()
                ));
    }
    private void runScript(String scriptPath) throws InterruptedException, IOException {

        String Output_line = "";

        ProcessBuilder Process_Builder = createProcessBuilder(scriptPath);

        Process Demo_Process = Process_Builder.start();

        Demo_Process.waitFor();

        BufferedReader Buffered_Reader = createBufferedReader(Demo_Process);

        while ((Output_line = Buffered_Reader.readLine()) != null) {
            System.out.println(Output_line);

        }
    }
    private boolean isPatent(Form form){
        return form.getTax() == taxService.get(PATENT);
    }
    @GetMapping()
    public String adminHome(Model model) {

        User curr_user = userService.getAuthenticatedUser();

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

        Subject subject = company.getSubject();

        model.addAttribute("curr_form",curr_form);
        model.addAttribute("curr_company",company);
        model.addAttribute("type","change");
        model.addAttribute("subject",subject);
        model.addAttribute("activ",new ActivitiesDto());

        System.out.println(company.getMainActivities());

        return "admin_home";
    }
    @PostMapping("/form/change")
    public String saveForm(@RequestParam(required = false) Long id, Model model,@ModelAttribute() ActivitiesDto aDto, BindingResult result){

        Form curr_form = formService.get(id);

        Company company = companyService.findByForm(curr_form);
        company.setMainActivities(aDto.getMainActivities());
        company.setActivities(aDto.getActivities());
        company = companyService.save(company);

        curr_form.setStatus(statusService.get(TO_FILL_OUT));
        curr_form = formService.save(curr_form);
        Subject subject = company.getSubject();
        String subjectName = subject.getName();
        return"redirect:/admin/form/make?id="+curr_form.getId();
    }
    @GetMapping("/form/make")
    public String makeDocs(@RequestParam(required = false) Long id, Model model) throws IOException, InterruptedException {


        runScript(MAKE_IP_SCRIPT_PATH);

        Form form = formService.get(id);

        if (isPatent(form)){
            System.out.println("импотент");
            runScript(MAKE_IP_PATENT_SCRIPT_PATH);
        }
        else{
            System.out.println("Утка");
            runScript(MAKE_IP_USN_SCRIPT_PATH);
        }

        form.setStatus(statusService.get(DONE));

        form = formService.save(form);

        return "redirect:/admin";
    }

}