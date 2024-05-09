package ru.studentproject.startbusiness.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import ru.studentproject.startbusiness.dto.UserRegistrationDto;
import ru.studentproject.startbusiness.repos.RoleRepository;


import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Entity
@Table(name = "users", uniqueConstraints =
@UniqueConstraint(columnNames = "email"))

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    @Email
    private String email;

    private String password;

    @ManyToMany(fetch = FetchType.EAGER,
            cascade = CascadeType.ALL)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id",
                    referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn
                    (name = "role_id",
                            referencedColumnName = "id"))
    private Collection<Role> roles;
    @Column(name = "form_count")
    @ColumnDefault("0")
    private int formsCount;
    public User() {

    }

    public User(String firstName, String lastName,
                String email, String password,
                Collection<Role> roles) {

        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }
    public User(UserRegistrationDto userRegistrationDto, Role role){


        final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        this.firstName = userRegistrationDto.getFirstName();
        this.lastName = userRegistrationDto.getLastName();
        this.email = userRegistrationDto.getEmail();
        this.password = passwordEncoder.encode(userRegistrationDto.getPassword());
        this.roles = Collections.singletonList(role);


    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Collection<Role> getRoles() {
        return roles;
    }

    public void setRoles(Collection<Role> roles) {
        this.roles = roles;
    }

    public int getFormsCount() {
        return formsCount;
    }

    public void setFormsCount() {
        this.formsCount =this.formsCount+1;
    }
}