package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
}
