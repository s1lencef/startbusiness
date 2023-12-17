package ru.studentproject.startbusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ru.studentproject.startbusiness.models.Types;
import ru.studentproject.startbusiness.repos.TypesRepository;

@Service
public class TaxService {
    @Autowired
    TypesRepository typesRepository;


    public Types get(Long id){
        return typesRepository.getReferenceById(id);
    }
}
