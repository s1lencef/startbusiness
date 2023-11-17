package ru.studentproject.startbusiness.service;

import ru.studentproject.startbusiness.dto.UserRegistrationDto;
import ru.studentproject.startbusiness.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {

    User save(UserRegistrationDto registrationDto);
    User findByEmail(String email);
    void updatePassword(String password, Long userId);
    List<User> getAll();

}