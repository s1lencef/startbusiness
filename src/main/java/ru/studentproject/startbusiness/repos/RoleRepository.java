package ru.studentproject.startbusiness.repos;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ru.studentproject.startbusiness.models.ERole;
import ru.studentproject.startbusiness.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}