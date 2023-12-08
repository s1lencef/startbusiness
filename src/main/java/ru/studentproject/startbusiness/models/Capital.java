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
    private double Value;
}
