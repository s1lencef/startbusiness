package ru.studentproject.startbusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.studentproject.startbusiness.models.Subject;
import ru.studentproject.startbusiness.repos.SubjectRepository;

@Service
public class SubjectService {
    @Autowired
    SubjectRepository subjectRepository;
    public Subject findByName(String name){
        return subjectRepository.findByName(name);
    }
}
