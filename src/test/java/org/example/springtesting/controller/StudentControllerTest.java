package org.example.springtesting.controller;

import org.example.springtesting.dto.StudentRequest;
import org.example.springtesting.dto.StudentResponse;
import org.example.springtesting.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class StudentControllerTest {

    @Mock
    private StudentService studentService;

    @InjectMocks
    private StudentController studentController;

    @Test
    void saveStudentTest() {
        StudentRequest studentRequest = new StudentRequest("John", "Smith", 10, "male", "John@gmail.com");
        StudentResponse studentResponse = StudentResponse.builder()
                .id(1L)
                .name("John")
                .surname("Smith")
                .build();
        when(studentService.save(Mockito.any(StudentRequest.class))).thenReturn(studentResponse);

        ResponseEntity<StudentResponse> response = studentController.save(studentRequest);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

}

