package ru.studentproject.startbusiness.repos;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Company;
import ru.studentproject.startbusiness.models.Form;
@Transactional
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    Company findByForm(Form form);
}
