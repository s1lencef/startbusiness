package ru.studentproject.startbusiness.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "documenttypes")
public class DocumentTypes {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;
}
