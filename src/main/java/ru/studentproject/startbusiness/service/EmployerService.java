package ru.studentproject.startbusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentproject.startbusiness.models.Employer;
import ru.studentproject.startbusiness.repos.EmployerRepository;
@Service
public class EmployerService {
    @Autowired
    EmployerRepository employerRepository;

    public Employer save(Employer employer){
        return employerRepository.save(employer);
    }
}
