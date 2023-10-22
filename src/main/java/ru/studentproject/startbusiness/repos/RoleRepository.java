package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.Role;


import java.util.Optional;


public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByName(String name);
}
