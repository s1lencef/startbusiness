package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Document;

import java.util.Collection;
import java.util.List;


@Repository
public interface DocumentRepository extends JpaRepository< Document, Long> {
    Document findByFilePath(String filePath);
    @Query("SELECT d FROM Document d WHERE d.type = false")
    Collection<Document> findSamples();
    @Query("SELECT d FROM Document d WHERE d.type = true")
    Collection <Document> findDocuments();


}
