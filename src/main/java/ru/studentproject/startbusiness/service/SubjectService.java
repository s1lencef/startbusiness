package ru.studentproject.startbusiness.service;

import jakarta.persistence.EntityNotFoundException;
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
    public Subject get(Long id){
        try {
            return subjectRepository.getReferenceById(id);
        }
        catch (EntityNotFoundException e){
            throw new RuntimeException("Регион не найден!");
        }

    }
    public Subject save(Subject subject){
        return subjectRepository.save(subject);
    }

}
