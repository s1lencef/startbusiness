package ru.studentproject.startbusiness.models;

import lombok.AllArgsConstructor;
import lombok.Data;
@AllArgsConstructor
@Data
public class Admin {
    private long id;
    private String username;
    private String fullName;
    private String password;
}
