package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.Employer;

public interface EmployerRepository extends JpaRepository<Employer,Long> {
}
