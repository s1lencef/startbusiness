package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import java.util.Date;

@Entity
public class Employer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long number;
    private int Citizenship;
    private String Surname;
    private String Name;
    private String Middlename;
    private int sex;
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
    private double Fraction;
    private double Percent;
    private String Post;



    public Employer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
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

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
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
