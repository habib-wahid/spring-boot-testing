package org.example.springtesting.service;

import org.example.springtesting.dto.StudentRequest;
import org.example.springtesting.dto.StudentResponse;
import org.example.springtesting.entity.Student;
import org.example.springtesting.mapper.StudentMapper;
import org.example.springtesting.repository.StudentRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @Test
    void saveStudentTest() {
        Student student = Student.builder()
                .id(1L)
                .name("John")
                .gender("male")
                .email("john@example.com")
                .surname("Smith")
                .age(10)
                .build();

        StudentRequest studentRequest = new StudentRequest(
                "John", "Smith", 10, "male", "john@example.com");

        StudentResponse studentResponse = StudentResponse.builder()
                .id(1L)
                .name("John")
                .gender("male")
                .email("john@example.com")
                .surname("Smith")
                .age(10)
                .build();

        when(studentRepository.save(Mockito.any(Student.class))).thenReturn(student);
        when(studentMapper.toStudentResponse(Mockito.any(Student.class))).thenReturn(studentResponse);

        studentResponse = studentService.save(studentRequest);

        assertNotNull(studentResponse);
        assertEquals(student.getId(), studentResponse.getId());
    }
}
