package ru.studentproject.startbusiness.service;

import org.springframework.beans.factory.annotation.Autowired;
import ru.studentproject.startbusiness.dto.UserRegistrationDto;
import ru.studentproject.startbusiness.models.Role;
import ru.studentproject.startbusiness.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.studentproject.startbusiness.repos.UserRepository;

import java.util.Collection;
import java.util.List;

public interface UserService extends UserDetailsService {
    User save(UserRegistrationDto registrationDto);
    User findByEmail(String email);
    List<User> findByRole(Long role);
    void updatePassword(String password, Long userId);
    User getAuthenticatedUser();

    User save(User user);

    List<User> getAll();
    User getById(Long id);

}