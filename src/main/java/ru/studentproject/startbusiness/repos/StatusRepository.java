package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Status;
@Repository
public interface StatusRepository extends JpaRepository<Status,Long> {
}