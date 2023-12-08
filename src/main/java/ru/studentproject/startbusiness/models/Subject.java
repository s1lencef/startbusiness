package ru.studentproject.startbusiness.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Subject {
    @Id
    private Long id;
    private String name;
    private int type;
}
