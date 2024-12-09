package org.example.springtesting.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springtesting.dto.StudentRequest;
import org.example.springtesting.dto.StudentResponse;
import org.example.springtesting.service.StudentService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
@AutoConfigureMockMvc
public class StudentControllerMvcTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void saveStudentTest() throws Exception {
        StudentResponse studentResponse = StudentResponse.builder()
                .id(1L)
                .name("John")
                .email("john@example.com")
                .surname("Smith")
                .age(10)
                .gender("male")
                .build();

        when(studentService.save(Mockito.any(StudentRequest.class))).thenReturn(studentResponse);

        StudentRequest studentRequest =
                new StudentRequest("John", "Smith", 10, "male", "John@gmail.com");

        mockMvc.perform(
                MockMvcRequestBuilders.post("/api/v1/student")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
    }


    @Test
    void getByIdTest() throws Exception {
        StudentResponse studentResponse = StudentResponse.builder()
                .id(1L)
                .name("John")
                .email("john@example.com")
                .surname("Smith")
                .age(10)
                .gender("male")
                .build();

        when(studentService.getStudentById(Mockito.anyLong())).thenReturn(studentResponse);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/student/1")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void getByIdInvalidTest() throws Exception {
        StudentResponse studentResponse = StudentResponse.builder()
                .id(1L)
                .name("John")
                .email("john@example.com")
                .surname("Smith")
                .age(10)
                .gender("male")
                .build();

        when(studentService.getStudentById(Mockito.anyLong())).thenReturn(studentResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/student/abc")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest());
    }

    @Test
    void getByNameTest() throws Exception {
        StudentResponse studentResponse = StudentResponse.builder()
                .id(1L)
                .name("John")
                .email("john@example.com")
                .surname("Smith")
                .age(10)
                .gender("male")
                .build();

        when(studentService.getStudentByName("John")).thenReturn(studentResponse);

        mockMvc.perform(
                        MockMvcRequestBuilders.get("/api/v1/student/name/John")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("John"));
    }

}
