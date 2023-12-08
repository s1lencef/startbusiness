package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.util.Date;

@Entity
public class Employer {
    @Id
    private Long id;
    private int Citizenship;
    private String Surname;
    private String Name;
    private String Middlename;
    private boolean sex;
    private Date BirthDate;
    private String BirthPlace;
    private Long INN;
    @Email
    private String email;
    private String phone;
    private int DocumentType;
    private Date IssueDate;
    private String IssuePlace;
    private String IssueCode;
    @ManyToOne(targetEntity = Subject.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "subject_code")
    private Subject SubjectCode;
    private String Locality;
    private String Street;
    private String Building;
    private String Office;

    private double Fraction;
    private double Percent;
    private String Post;


}
