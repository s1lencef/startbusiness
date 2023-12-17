package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.Subject;

public interface SubjectRepository extends JpaRepository<Subject,Long> {
    public Subject findByName(String name);
}
