package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.Status;

public interface StatusRepository extends JpaRepository<Status,Long> {
}