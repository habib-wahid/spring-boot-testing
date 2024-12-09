package org.example.springtesting.mapper;

import org.example.springtesting.dto.StudentResponse;
import org.example.springtesting.entity.Student;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class StudentMapperTest {

    private StudentMapper studentMapper = new StudentMapper();

    @Test
    void studentResponseTest() {
        Student student = new Student(1L, "John", "Doe", 10, "male", "John@example.com");
        StudentResponse studentResponse = studentMapper.toStudentResponse(student);
        Assertions.assertNotNull(studentResponse);
        assertEquals(student.getId(), studentResponse.getId());
    }

    @Test
    void studentResponseNullTest() {
        Student student = null;
        RuntimeException exception = Assertions.assertThrows(RuntimeException.class, () -> studentMapper.toStudentResponse(student));
        assertEquals(exception.getMessage(), "Student object is null");
    }
}
