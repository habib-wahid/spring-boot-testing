package org.example.springtesting.dto;

import lombok.*;

@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class StudentResponse {
    private Long id;
    private String name;
    private String surname;
    private String email;
    private int age;
    private String gender;
}
