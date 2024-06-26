package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;

@Entity
public class Capital {
    @Id
    private Long id;
    @OneToOne(targetEntity = Form.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "form_id")
    private Form formId;
    private int Type;
    private double value;

    public Capital(Long id, Form formId, int type, double value) {
        this.id = id;
        this.formId = formId;
        Type = type;
        this.value = value;
    }

    public Capital() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Form getFormId() {
        return formId;
    }

    public void setFormId(Form formId) {
        this.formId = formId;
    }

    public int getType() {
        return Type;
    }

    public void setType(int type) {
        Type = type;
    }

    public double getvalue() {
        return value;
    }

    public void setvalue(double value) {
        this.value = value;
    }
}
