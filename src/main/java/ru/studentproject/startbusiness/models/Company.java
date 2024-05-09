package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.ColumnDefault;

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
    private String mainActivities;
    private String Activities;
    private int CharterType = 22;
    @ManyToOne(targetEntity = Document.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "charter_id")
    private Document Charter;
    @ManyToOne(targetEntity = Subject.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = true, name = "subject_id")
    private Subject subject;
    private String Locality;
    @ColumnDefault("0")
    private int localityId;
    @ColumnDefault("0")
    private int localityType;
    private String Street;
    private String Building;
    private String Office;
    @Column(name = "cabinet")
    private String cabinet;
    @Email
    private String email;

    @ColumnDefault("false")
    private boolean haveEmp;
    @ColumnDefault("0")
    private Integer employersCount;
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

    public int getLocalityId() {
        return localityId;
    }

    public void setLocalityId(int localityId) {
        this.localityId = localityId;
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

    public String getMainActivities() {
        return mainActivities;
    }

    public void setMainActivities(String mainActivities) {
        this.mainActivities = mainActivities;
    }

    public String getCabinet() {
        return cabinet;
    }

    public void setCabinet(String cabinet) {
        this.cabinet = cabinet;
    }

    public boolean isHaveEmp() {
        return haveEmp;
    }

    public void setHaveEmp(boolean haveEmp) {
        this.haveEmp = haveEmp;
    }


    public void setEmployersCount(Integer employersCount) {
        this.employersCount = employersCount;
    }

    public int getLocalityType() {
        return localityType;
    }

    public void setLocalityType(int localityType) {
        this.localityType = localityType;
    }

    public Integer getEmployersCount() {
        return employersCount;
    }
}
