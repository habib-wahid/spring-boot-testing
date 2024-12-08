package org.example.springtesting.mapper;

import org.example.springtesting.dto.StudentResponse;
import org.example.springtesting.entity.Student;
import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public StudentResponse toStudentResponse(Student student) {
        if (student == null) {
            throw new RuntimeException("Student object is null");
        }
        return StudentResponse.builder()
                .id(student.getId())
                .name(student.getName())
                .age(student.getAge())
                .email(student.getEmail())
                .gender(student.getGender())
                .build();
    }
}
