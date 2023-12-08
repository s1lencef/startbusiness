package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;

@Entity
@Table(name = "status")
public class Status {
    @Id
    private Long id;
    @Column(name = "name")
    private String name;

    public Status(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Status() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
