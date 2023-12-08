package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.Form;

public interface FormRepository extends JpaRepository<Form,Long> {
}
