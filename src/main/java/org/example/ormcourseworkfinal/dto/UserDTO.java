package org.example.ormcourseworkfinal.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import lombok.*;
import org.example.ormcourseworkfinal.entity.Student;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class UserDTO {
    private String userId;
    private String username;
    private String password;
}
