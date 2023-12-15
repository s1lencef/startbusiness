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

    public Employer(Long id, int citizenship, String surname, String name, String middlename,
                    boolean sex, Date birthDate, String birthPlace, Long INN, String email,
                    String phone, int documentType, Date issueDate, String issuePlace,
                    String issueCode, Subject subjectCode, String locality, String street,
                    String building, String office, double fraction, double percent, String post) {
        this.id = id;
        Citizenship = citizenship;
        Surname = surname;
        Name = name;
        Middlename = middlename;
        this.sex = sex;
        BirthDate = birthDate;
        BirthPlace = birthPlace;
        this.INN = INN;
        this.email = email;
        this.phone = phone;
        DocumentType = documentType;
        IssueDate = issueDate;
        IssuePlace = issuePlace;
        IssueCode = issueCode;
        SubjectCode = subjectCode;
        Locality = locality;
        Street = street;
        Building = building;
        Office = office;
        Fraction = fraction;
        Percent = percent;
        Post = post;
    }

    public Employer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getCitizenship() {
        return Citizenship;
    }

    public void setCitizenship(int citizenship) {
        Citizenship = citizenship;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getMiddlename() {
        return Middlename;
    }

    public void setMiddlename(String middlename) {
        Middlename = middlename;
    }

    public boolean isSex() {
        return sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public Date getBirthDate() {
        return BirthDate;
    }

    public void setBirthDate(Date birthDate) {
        BirthDate = birthDate;
    }

    public String getBirthPlace() {
        return BirthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        BirthPlace = birthPlace;
    }

    public Long getINN() {
        return INN;
    }

    public void setINN(Long INN) {
        this.INN = INN;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getDocumentType() {
        return DocumentType;
    }

    public void setDocumentType(int documentType) {
        DocumentType = documentType;
    }

    public Date getIssueDate() {
        return IssueDate;
    }

    public void setIssueDate(Date issueDate) {
        IssueDate = issueDate;
    }

    public String getIssuePlace() {
        return IssuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        IssuePlace = issuePlace;
    }

    public String getIssueCode() {
        return IssueCode;
    }

    public void setIssueCode(String issueCode) {
        IssueCode = issueCode;
    }

    public Subject getSubjectCode() {
        return SubjectCode;
    }

    public void setSubjectCode(Subject subjectCode) {
        SubjectCode = subjectCode;
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

    public double getFraction() {
        return Fraction;
    }

    public void setFraction(double fraction) {
        Fraction = fraction;
    }

    public double getPercent() {
        return Percent;
    }

    public void setPercent(double percent) {
        Percent = percent;
    }

    public String getPost() {
        return Post;
    }

    public void setPost(String post) {
        Post = post;
    }
}
