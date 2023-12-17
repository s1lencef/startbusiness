package ru.studentproject.startbusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentproject.startbusiness.models.Company;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.repos.CompanyRepository;

@Service
public class CompanyService {
    @Autowired
    CompanyRepository companyRepository;
    public Company save(Company company){

        return companyRepository.save(company);
    }
}
