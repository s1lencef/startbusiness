package ru.studentproject.startbusiness.repos;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.studentproject.startbusiness.models.Types;

public interface TypesRepository extends JpaRepository<Types, Long> {
}
