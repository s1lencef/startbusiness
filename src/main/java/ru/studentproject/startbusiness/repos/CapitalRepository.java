package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.studentproject.startbusiness.models.Capital;

public interface CapitalRepository extends JpaRepository<Capital, Long> {
    Capital findByValue(double value);
}
