package org.example.springtesting.controller;

import lombok.RequiredArgsConstructor;
import org.example.springtesting.dto.StudentRequest;
import org.example.springtesting.dto.StudentResponse;
import org.example.springtesting.service.StudentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/student")
@RequiredArgsConstructor
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public ResponseEntity<StudentResponse> save(@RequestBody StudentRequest studentRequest) {
        return new ResponseEntity<>(studentService.save(studentRequest), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentResponse> getById(@PathVariable Long id) {
        return new ResponseEntity<>(studentService.getStudentById(id), HttpStatus.OK);
    }

    @GetMapping("/{name}")
    public ResponseEntity<StudentResponse> getByName(@PathVariable String name) {
        return new ResponseEntity<>(studentService.getStudentByName(name), HttpStatus.OK);
    }

}
