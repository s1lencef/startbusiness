package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.DocumentTypes;
import ru.studentproject.startbusiness.models.Form;
import ru.studentproject.startbusiness.models.User;

import java.util.List;
@Repository
public interface FormRepository extends JpaRepository<Form,Long> {
    List<Form> findByUser(User user);

    List<Form> findByStaff(User staff);
}
