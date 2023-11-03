package ru.studentproject.startbusiness.service;

import ru.studentproject.startbusiness.dto.UserRegistrationDto;
import ru.studentproject.startbusiness.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);

    List<User> getAll();
}