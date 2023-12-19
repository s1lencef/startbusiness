package ru.studentproject.startbusiness.dto;

import jakarta.validation.constraints.Email;


import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormDto {

    private String firstName;
    private String lastName;
    private String middleName;
    private int citizenship;
    private Date birthDate;
    private String birthPlace;
    private Long iNN;
    private int sex;
    @Email
    private String email;
    private String phone;
    private int documentType;
    private Long number;//серия и номер паспорта
    private Date issueDate;
    private String issuePlace;
    private String issueCode;
    private String subject;
    private String locality;
    private String street;
    private String building;
    private String office;
    private String mainActivities;
    private String activities;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String midlleName) {
        this.middleName = midlleName;
    }

    public int getCitizenship() {
        return citizenship;
    }

    public void setCitizenship(int citizenship) {
        this.citizenship = citizenship;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public Long getiNN() {
        return iNN;
    }

    public void setiNN(Long iNN) {
        this.iNN = iNN;
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
        return documentType;
    }

    public void setDocumentType(int documentType) {
        this.documentType = documentType;
    }

    public Long getNumber() {
        return number;
    }

    public void setNumber(Long number) {
        this.number = number;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public String getIssuePlace() {
        return issuePlace;
    }

    public void setIssuePlace(String issuePlace) {
        this.issuePlace = issuePlace;
    }

    public String getIssueCode() {
        return issueCode;
    }

    public void setIssueCode(String issueCode) {
        this.issueCode = issueCode;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getMainActivities() {
        return mainActivities;
    }

    public void setMainActivities(String mainActivities) {
        this.mainActivities = mainActivities;
    }

    public String getActivities() {
        return activities;
    }

    public void setActivities(String activities) {
        this.activities = activities;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getLocality() {
        return locality;
    }

    public void setLocality(String locality) {
        this.locality = locality;
    }
    public String toString(){
        return getLastName()+"&"+getFirstName()+"&"+getMiddleName()+"&"+getCitizenship()+"&"+new SimpleDateFormat("dd-MM-yyyy").format(getBirthDate())
                +"&"+getBirthPlace()+"&"+getiNN()+"&"+getSex()+"&"+getEmail()+"&"+getPhone()+"&"+getDocumentType()+"&"+getNumber()+"&"+getIssueDate()+"&"+getIssuePlace()
                + "&"+getSubject() + "&" + getLocality() + "&" + getStreet() + "&" + getBuilding() + "&" + getOffice() + "&"+getMainActivities()+"&"+getActivities();

    }

    public FormDto() {
        this.firstName = "Максим";
        this.lastName = "Авиатков";
        this.middleName = "Семенович";
        this.citizenship = 123;
        Calendar now = Calendar.getInstance();
        this.birthDate = now.getTime();
        this.birthPlace = "Сургутуралкирпич";
        this.iNN = 1234567898L;
        this.sex = 1;
        this.email="maxim@mail.ru";
        this.phone="79211113314";
        this.documentType = 21;
        this.number=1234556890L;//серия и номер паспорта
        this.issueDate = now.getTime();
        this.issuePlace = "МП №9 УФМС РФ Выборгского района Санкт-Петербурга по Санкт-Петербургу и Ленинградской области";
        this.issueCode = "123-123";
        this.subject = "78";
        this.locality = "г.Санкт-Петербруг";
        this.street="ул.Папова";
        this.building="1";
        this.office = "12";
        this.mainActivities = "43.68.12";
        this.activities = "43.68.13 43.68.13";
    }
}
