package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;

@Entity
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Form.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "form_id")
    private Form form;
    private String FullName;
    private String ShortName;
    @Lob
    private String Activities;
    private int CharterType = 22;
    @ManyToOne(targetEntity = Document.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "charter_id")
    private Document Charter;
    @ManyToOne(targetEntity = Subject.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "subject_id")
    private Subject subject;
    private String Locality;
    private String Street;
    private String Building;
    private String Office;
    @Email
    private String email;

    public Company(Long id, String fullName, String shortName,
                   String activities, int charterType, Document charter,
                   Subject subject, String locality, String street,
                   String building, String office, String email)
    {
        this.id = id;
        FullName = fullName;
        ShortName = shortName;
        Activities = activities;
        CharterType = charterType;
        Charter = charter;
        this.subject = subject;
        Locality = locality;
        Street = street;
        Building = building;
        Office = office;
        this.email = email;
    }

    public Company() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getShortName() {
        return ShortName;
    }

    public void setShortName(String shortName) {
        ShortName = shortName;
    }

    public String getActivities() {
        return Activities;
    }

    public void setActivities(String activities) {
        Activities = activities;
    }

    public int getCharterType() {
        return CharterType;
    }

    public void setCharterType(int charterType) {
        CharterType = charterType;
    }

    public Document getCharter() {
        return Charter;
    }

    public void setCharter(Document charter) {
        Charter = charter;
    }

    public Subject getSubject() {
        return subject;
    }

    public void setSubject(Subject subject) {
        this.subject = subject;
    }

    public String getLocality() {
        return Locality;
    }

    public void setLocality(String locality) {
        Locality = locality;
    }

    public String getStreet() {
        return Street;
    }

    public void setStreet(String street) {
        Street = street;
    }

    public String getBuilding() {
        return Building;
    }

    public void setBuilding(String building) {
        Building = building;
    }

    public String getOffice() {
        return Office;
    }

    public void setOffice(String office) {
        Office = office;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Form getForm() {
        return form;
    }

    public void setForm(Form form) {
        this.form = form;
    }
}
