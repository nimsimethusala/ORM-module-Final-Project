package org.example.ormcourseworkfinal.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Student {
    @Id
    private String studentId;
    private String name;
    private int contact;
    private String address;
    private String email;

    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    private List<Registration> registrations;

    public Student(String studentId, String name, int contact, String address, String email) {
        this.studentId = studentId;
        this.name = name;
        this.contact = contact;
        this.address = address;
        this.email = email;
    }
}
