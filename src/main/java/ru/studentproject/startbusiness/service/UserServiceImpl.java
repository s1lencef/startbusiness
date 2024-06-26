package ru.studentproject.startbusiness.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import ru.studentproject.startbusiness.dto.UserRegistrationDto;
import ru.studentproject.startbusiness.models.User;
import ru.studentproject.startbusiness.models.Role;
import ru.studentproject.startbusiness.repos.RoleRepository;
import ru.studentproject.startbusiness.repos.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    @Autowired(required=true)
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository, BCryptPasswordEncoder passwordEncoder) {
        super();
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        Role role = roleRepository.findByName("ROLE_USER");

        var user = new User(registrationDto,role);

        return userRepository.save(user);
    }
    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        var user = userRepository.findByEmail(username);

        if (user == null) {
            throw new UsernameNotFoundException
                    ("Invalid username or password.");
        }
        return new org.springframework.security
                .core.userdetails.User(user.getEmail(),
                user.getPassword(),
                mapRolesToAuthorities(user.getRoles()));
    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<Role> roles) {

        return roles.stream()
                .map(role -> new SimpleGrantedAuthority
                        (role.getName()))
                .collect(Collectors.toList());
    }
    public User getAuthenticatedUser(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        return findByEmail(email);
    }

    @Override
    public List<User> getAll() {

        return userRepository.findAll();
    }
    public User findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    public List<User> findByRole(Long role) {
        return userRepository.findByRole(role);
    }

    public void updatePassword(String password, Long userId) {
        userRepository.updatePassword(password, userId);
    }
    public User getById(Long id){
        return  userRepository.getReferenceById(id);
    }

}