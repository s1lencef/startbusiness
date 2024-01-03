package ru.studentproject.startbusiness.dto;

import jakarta.validation.constraints.Email;
import org.hibernate.annotations.ColumnDefault;


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
    private int residentCard;
    private String residentCardNumber;
    private Date residentCardIssueDate;
    private String residentCardIssuePlace;
    private Date residentCardEndDate;
    private int infiniteResidentCard;
    private String subject;
    private String locality;
    private String street;
    private String building;
    private String office;
    private String cabinet;
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

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public String toString(){
        return getLastName()+"&"+getFirstName()+"&"+getMiddleName()+"&"+getCitizenship()+"&"+new SimpleDateFormat("dd-MM-yyyy").format(getBirthDate())
                +"&"+getBirthPlace()+"&"+getiNN()+"&"+getSex()+"&"+getEmail()+"&"+getPhone()+"&"+getDocumentType()+"&"+getNumber()+"&"+getIssueDate()+"&"+getIssuePlace()
                + "&"+getSubject() + "&" + getLocality() + "&" + getStreet() + "&" + getBuilding() + "&" + getOffice() + "&"+getMainActivities()+"&"+getActivities();

    }

    public int getResidentCard() {
        return this.residentCard;
    }

    public void setResidentCard(int residentCard) {
        this.residentCard = residentCard;
    }

    public String getResidentCardNumber() {
        return residentCardNumber;
    }

    public void setResidentCardNumber(String residentCardNumber) {
        this.residentCardNumber = residentCardNumber;
    }

    public Date getResidentCardIssueDate() {
        return residentCardIssueDate;
    }

    public void setResidentCardIssueDate(Date residentCardIssueDate) {
        this.residentCardIssueDate = residentCardIssueDate;
    }

    public String getResidentCardIssuePlace() {
        return residentCardIssuePlace;
    }

    public void setResidentCardIssuePlace(String residentCardIssuePlace) {
        this.residentCardIssuePlace = residentCardIssuePlace;
    }

    public Date getResidentCardEndDate() {
        return residentCardEndDate;
    }

    public void setResidentCardEndDate(Date residentCardEndDate) {
        this.residentCardEndDate = residentCardEndDate;
    }

    public int getInfiniteResidentCard() {
        return infiniteResidentCard;
    }

    public void setInfiniteResidentCard(int infiniteResidentCard) {
        this.infiniteResidentCard = infiniteResidentCard;
    }

    public FormDto() {
        this.infiniteResidentCard = 2;
    }
}
