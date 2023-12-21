package ru.studentproject.startbusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import ru.studentproject.startbusiness.models.Status;
import ru.studentproject.startbusiness.repos.StatusRepository;

import java.util.List;

@Service
public class StatusService {
    @Autowired
    StatusRepository statusRepository;


    public Status get(Long id){
        return statusRepository.getReferenceById(id);
    }
}