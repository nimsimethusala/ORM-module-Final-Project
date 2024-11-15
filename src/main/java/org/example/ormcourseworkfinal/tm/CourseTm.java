package org.example.ormcourseworkfinal.tm;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class CourseTm {
    private String CourseId;
    private String CourseName;
    private String Duration;
    private double programFee;
}
