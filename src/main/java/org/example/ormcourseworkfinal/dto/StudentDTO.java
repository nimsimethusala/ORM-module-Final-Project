package org.example.ormcourseworkfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentDTO {
    private String StudentId;
    private String name;
    private int contact;
    private String address;
    private String email;
}
