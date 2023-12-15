package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;

import java.util.List;

public interface FormRepository extends JpaRepository<Form,Long> {
    List<Form> findByUser(User user);
}
