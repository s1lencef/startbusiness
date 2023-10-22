package ru.studentproject.startbusiness.repos;

import ru.studentproject.startbusiness.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);
}
