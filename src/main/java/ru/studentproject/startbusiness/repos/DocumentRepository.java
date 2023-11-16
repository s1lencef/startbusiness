package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Document;


@Repository
public interface DocumentRepository extends JpaRepository< Document, Long> {
    Document findByFilePath(String filePath);

}
