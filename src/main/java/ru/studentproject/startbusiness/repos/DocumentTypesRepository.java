package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Document;
import ru.studentproject.startbusiness.models.DocumentTypes;

@Repository
public interface DocumentTypesRepository extends JpaRepository<DocumentTypes, Long> {
    DocumentTypes findByName(String name);
}
