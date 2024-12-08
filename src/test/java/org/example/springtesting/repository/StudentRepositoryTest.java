package org.example.springtesting.repository;

import org.example.springtesting.entity.Student;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
@Testcontainers
public class StudentRepositoryTest {

    @Autowired
    private StudentRepository studentRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13-alpine")
            .withDatabaseName("test_db")
            .withUsername("postgres")
            .withPassword("root");

    @Test
    void findByNameTest() {
        Student student = new Student();
        student.setId(null);
        student.setName("John");
        student.setAge(10);
        student.setEmail("john@example.com");
        student.setGender("male");
        studentRepository.save(student);

        Student savedStudent = studentRepository.findByName("John");
        assertNotNull(savedStudent);
        assertEquals(savedStudent.getId(), 1L);
        assertEquals(savedStudent.getName(), "John");
    }

    @Test
    void findByNameNotFoundTest() {
        Student savedStudent = studentRepository.findByName("John");
        assertNull(savedStudent);
    }
}
