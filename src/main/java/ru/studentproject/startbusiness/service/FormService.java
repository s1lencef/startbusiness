package ru.studentproject.startbusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.repos.FormRepository;
import ru.studentproject.startbusiness.repos.TypesRepository;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
@Service
public class FormService {
    @Autowired
    FormRepository formRepository;
    @Autowired
    StatusService statusService;
    @Autowired
    TaxService taxService;


    public List<Form> getUsersForms(User user) {
        List<Form> forms =  formRepository.findByUser(user);
        forms.sort(new FormComparator());
        for (Form form :forms){
            System.out.println(form.getId());
        }
        return forms;
    }
    public List<Form> getAdminForms(User user) {
        return formRepository.findByStaff(user);
    }
    public Form get(Long id){
        return formRepository.getReferenceById(id);
    }
    public Form save(Long taxId, User currUser) {

        Form form = new Form();
        form.setDate();
        form.setType(true);
        form.setStaff(null);
        form.setStatus(statusService.get(1L));
        form.setTax(taxService.get(taxId));
        form.setUser(currUser);

        return formRepository.save(form);
    }
    public Form save(Form form) {
        return formRepository.save(form);
    }
    public void delete(Form form){
        formRepository.delete(form);
    }
}
