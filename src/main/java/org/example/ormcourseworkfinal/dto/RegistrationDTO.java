package org.example.ormcourseworkfinal.dto;

import lombok.*;
import org.example.ormcourseworkfinal.entity.Course;
import org.example.ormcourseworkfinal.entity.Student;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class RegistrationDTO {
    private String registrationId;
    private Date date;
    private double upfrontPayment;
    private Student student;
    private Course course;
}
