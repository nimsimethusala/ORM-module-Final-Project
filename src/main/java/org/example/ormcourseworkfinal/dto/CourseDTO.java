package org.example.ormcourseworkfinal.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseDTO {
    private String CourseId;
    private String CourseName;
    private String Duration;
    private double programFee;
}
