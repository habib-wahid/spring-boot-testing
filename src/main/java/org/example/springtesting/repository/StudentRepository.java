package org.example.springtesting.repository;

import org.example.springtesting.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Student findByName(String firstName);
}
