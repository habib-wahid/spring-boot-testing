package org.example.springtesting.service;

import lombok.RequiredArgsConstructor;
import org.example.springtesting.dto.StudentRequest;
import org.example.springtesting.dto.StudentResponse;
import org.example.springtesting.entity.Student;
import org.example.springtesting.mapper.StudentMapper;
import org.example.springtesting.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class StudentService {
    private final StudentRepository studentRepository;
    private final StudentMapper studentMapper;

    public StudentResponse save(StudentRequest studentRequest) {
        Student student = Student.builder()
                .name(studentRequest.name())
                .surname(studentRequest.surname())
                .age(studentRequest.age())
                .email(studentRequest.email())
                .gender(studentRequest.gender())
                .build();
        Student savedStudent = studentRepository.save(student);
        return studentMapper.toStudentResponse(savedStudent);
    }

    public StudentResponse getStudentById(Long id) {
        return studentMapper.toStudentResponse(studentRepository.findById(id).orElse(null));
    }

    public StudentResponse getStudentByName(String name) {
        return studentMapper.toStudentResponse(studentRepository.findByName(name));
    }
}
