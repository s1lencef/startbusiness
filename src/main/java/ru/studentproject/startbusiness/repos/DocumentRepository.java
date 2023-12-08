package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Document;
import ru.studentproject.startbusiness.models.DocumentTypes;

import java.util.Collection;
import java.util.List;


@Repository
public interface DocumentRepository extends JpaRepository< Document, Long> {
    Document findByFilePath(String filePath);
    @Query("SELECT d FROM Document d WHERE d.type = :type")
    List<Document> findByType(@Param("type") DocumentTypes type);


}
