package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class Form {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = DocumentTypes.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "user_id")
    private User user;
    @ManyToOne(targetEntity = DocumentTypes.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "staff_id")
    private User staff;
    private boolean type;
    private Date date;
    @ManyToOne(targetEntity = DocumentTypes.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "status")
    private Status status;


}
