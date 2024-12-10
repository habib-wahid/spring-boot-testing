package org.example.springtesting.integration_with_testcontainer;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springtesting.dto.StudentRequest;
import org.example.springtesting.entity.Student;
import org.example.springtesting.repository.StudentRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
public class StudentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Container
    @ServiceConnection
    static PostgreSQLContainer<?> postgreSQLContainer = new PostgreSQLContainer<>("postgres:13-alpine")
            .withDatabaseName("test_db")
            .withUsername("postgres")
            .withPassword("postgres");

    @Test
    void saveStudentTest() throws Exception {
        StudentRequest studentRequest = new StudentRequest("John", "smith", 10, "male", "john.smith@gmail.com");
        mockMvc.perform(
                        MockMvcRequestBuilders.post("/api/v1/student")
                                .contentType(MediaType.APPLICATION_JSON)
                                .accept(MediaType.APPLICATION_JSON)
                                .content(objectMapper.writeValueAsString(studentRequest)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("John"));
    }

    @Test
    void getStudentTest() throws Exception {

        Student student = new Student();
        student.setId(null);
        student.setName("Habib");
        student.setSurname("Wahid");
        student.setGender("male");
        student.setAge(10);
        student.setEmail("habib@gmail.com");

        studentRepository.save(student);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/student/name/Habib")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }

}
