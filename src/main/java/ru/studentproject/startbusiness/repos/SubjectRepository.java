package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Subject;
@Repository
public interface SubjectRepository extends JpaRepository<Subject,Long> {
    public Subject findByName(String name);
}
