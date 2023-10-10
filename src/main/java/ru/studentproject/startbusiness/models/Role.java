package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERole name;

    public Integer getId() {
        return id;
    }

    public ERole getName() {
        return name;
    }

    public Role() {

    }

    public Role(ERole name) {
        this.name = name;
    }

    // getters and setters
}