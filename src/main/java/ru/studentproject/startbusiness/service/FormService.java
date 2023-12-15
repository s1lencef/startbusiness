package ru.studentproject.startbusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.repos.FormRepository;


import java.util.List;
@Service
public class FormService {
    @Autowired
    FormRepository formRepository;

    public List<Form> getUsersForms(User user) {
        return formRepository.findByUser(user);
    }
}
