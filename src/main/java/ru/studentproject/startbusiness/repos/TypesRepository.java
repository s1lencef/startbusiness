package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.stereotype.Repository;
import ru.studentproject.startbusiness.models.Types;
@Repository
public interface TypesRepository extends JpaRepository<Types, Long> {
}
