package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Employer;
import ru.studentproject.startbusiness.models.Form;
@Repository
public interface EmployerRepository extends JpaRepository<Employer,Long> {
    Employer findByForm(Form form);
}
