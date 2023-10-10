package ru.studentproject.startbusiness.repos;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.User;

import java.util.Optional;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.studentproject.startbusiness.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);
}