package ru.studentproject.startbusiness.dto;

import jakarta.validation.constraints.Email;
import ru.studentproject.startbusiness.models.Subject;

import java.util.Date;

public class FormDto {

    private String firstName;
    private String LastName;
    private String midlleName;
    private int citizenship;
    private Date birthDate;
    private String birthPlace;
    private Long iNN;
    @Email
    private String email;
    private String phone;
    private int documentType;
    private Long number;//серия и номер паспорта
    private Date issueDate;
    private String issuePlace;
    private String issueCode;
    private Subject subjectCode;
    private String locality;
    private String street;
    private String building;
    private String office;
    private String mainActivities;
    private String activities;



}
