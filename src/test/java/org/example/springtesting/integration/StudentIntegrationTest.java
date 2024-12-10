package org.example.springtesting.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.springtesting.dto.StudentRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

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
    void getStudentByIdTest() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/api/v1/student/2")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(status().isOk());
    }
}
