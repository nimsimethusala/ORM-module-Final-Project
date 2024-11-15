package org.example.ormcourseworkfinal.entity;

import jakarta.persistence.*;
import lombok.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Registration {
    @Id
    private String registrationId;
    private Date date;
    private double upfrontPayment;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Course course;

    @OneToMany(mappedBy = "registration", cascade = CascadeType.ALL)
    private List<Payment> payments;
}
