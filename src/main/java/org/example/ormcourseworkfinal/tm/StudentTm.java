package org.example.ormcourseworkfinal.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class StudentTm {
    private String StudentId;
    private String name;
    private int contact;
    private String address;
    private String email;
}
