package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
public class Company {
    @Id
    private Long id;
    private String FullName;
    private String ShortName;
    @Lob
    private String Activities;
    private int CharterType = 22;
    @ManyToOne(targetEntity = Document.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "charter_id")
    private Document Charter;
    @ManyToOne(targetEntity = Subject.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "subject_id")
    private Subject subject;
    private String Locality;
    private String Street;
    private String Building;
    private String Office;
    @Email
    private String email;
}
