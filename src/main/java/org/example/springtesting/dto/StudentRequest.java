package org.example.springtesting.dto;
public record StudentRequest(
        String name,
        String surname,
        Integer age,
        String gender,
        String email) {}
