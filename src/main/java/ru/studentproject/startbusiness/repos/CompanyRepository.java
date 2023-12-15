package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
}
